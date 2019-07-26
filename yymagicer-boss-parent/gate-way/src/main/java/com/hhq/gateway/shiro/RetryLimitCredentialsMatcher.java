package com.hhq.gateway.shiro;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 登陆错误次数限制 <Description> <br>
 *
 */
@Component
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

    /**
     * redis保存key的前缀
     */
    public static final String TRY_TIMES_PREFIX = "hhq:user:login:tryTimes:";

    /**
     * 加密迭代次数
     */
    public static final int HASHITERATIONS = 1024;

    /**
     * 登陆尝试错误次数
     */
    @Value("${login.try.times}")
    private int loginTryTimes;

    /**
     * 登陆锁定后的时长
     */
    @Value("${login.try.timeout.seconds}")
    private int loginTryTimeoutSeconds;

    /**
     * redis客户端
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 默认构造函数
     */
    public RetryLimitCredentialsMatcher() {
        this.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        this.setHashIterations(HASHITERATIONS);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String userName = (String) token.getPrincipal();
        String redisKey = TRY_TIMES_PREFIX + userName;
        Integer times = (Integer) redisTemplate.opsForValue().get(redisKey);
        boolean needDeleteRedisKey = true;
        if (times == null) {
            times = 0;
            needDeleteRedisKey = false;
        }
        if (times.intValue() >= loginTryTimes) {
            throw new ExcessiveAttemptsException("username: " + userName + " tried to login more than 5 times in period.");
        }
        boolean matched = super.doCredentialsMatch(token, info);
        if (matched) {
            // 判断一下times是否为空，从而减少一次redis访问
            if (needDeleteRedisKey) {
                redisTemplate.delete(redisKey);
            }
        } else {
            redisTemplate.opsForValue().set(redisKey, ++times, loginTryTimeoutSeconds, TimeUnit.SECONDS);
        }
        return matched;

    }

    /**
     * 解锁由于登录错误次数超过限定的用户 Description: <br>
     * 
     * @author zhangshuxing<br>
     * @taskId <br>
     * @param userName <br>
     */
    public void unlockLoginDisabledUser(String userName) {
        String redisKey = TRY_TIMES_PREFIX + userName;
        redisTemplate.delete(redisKey);
    }

    /** 
     * set redisTemplate
     * @param redisTemplate The redisTemplate to set. <br>
     */
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /** 
     * set loginTryTimes
     * @param loginTryTimes The loginTryTimes to set. <br>
     */
    public void setLoginTryTimes(int loginTryTimes) {
        this.loginTryTimes = loginTryTimes;
    }

    /** 
     * set loginTryTimeoutSeconds
     * @param loginTryTimeoutSeconds The loginTryTimeoutSeconds to set. <br>
     */
    public void setLoginTryTimeoutSeconds(int loginTryTimeoutSeconds) {
        this.loginTryTimeoutSeconds = loginTryTimeoutSeconds;
    }
}
