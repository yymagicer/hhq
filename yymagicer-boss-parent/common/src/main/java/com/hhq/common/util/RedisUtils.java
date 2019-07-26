package com.hhq.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    /**
     * 引入redisTemplate
     */
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 五分钟，秒级
     */
    public static final long FIVE_MINUTE = 60L * 5;

    /**
     * 15分钟，秒级
     */
    public static final long QUARTER = 60L * 15;

    /**
     * 7天，秒级
     */
    public static final long ONE_WEEK = 60L * 60 * 24 * 7;

    /**
     * 6小时，秒级
     */
    public static final long SIX_HOUR = 60L * 60 * 6;

    /**
     * 一小时，秒级
     */
    public static final long ONE_HOUR = 60L * 60;

    /**
     * 30天，秒级
     */
    public static final long ONE_MONTH = 60L * 60 * 24 * 30;

    /**
     * 一天，秒级
     */
    public static final long ONE_DAY = 60L * 60 * 24;

    /**
     * Description: redis原子性自增操作<br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key key
     * @param delta 每次自增量
     * @return 增加后的值<br>
     */
    public long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * Description: 给key设置超时时间<br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key <br>
     * @param expireTime <br>
     */
    public void setExpire(String key, long expireTime) {
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * Description: 批量删除<br>
     * （该操作会执行模糊查询，请尽量不要使用，以免影响性能或误删）
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param pattern redis的key<br>
     */
    public void batchDel(String... pattern) {
        for (String kp : pattern) {
            redisTemplate.delete(redisTemplate.keys(kp + "*"));
        }
    }

    /**
     * Description: redis set操作<br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key key
     * @param value value<br>
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * Description: redis set操作, 同时设置超时时间<br>
     * Set the {@code value} and expiration {@code expireTime} for {@code key}.<br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key key
     * @param value value<br>
     * @param expireTime 超时时间<br>
     */
    public void setValueWithExpire(String key, String value, long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * Description: 从左侧向redis的list中推数据<br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key <br>
     * @param values <br>
     */
    public void leftPushToList(String key, String... values) {
        redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * Description: 判断value值存不存在于key对应的set中<br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key key
     * @param value value
     * @return <br>
     */
    public boolean isValueExistInSet(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * Description: 向key对应的set中增加指定的值<br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key key
     * @param values 要添加的值<br>
     */
    public void addValueToSet(String key, String[] values) {
        redisTemplate.opsForSet().add(key, values);
    }
    /**
     * Description: redis del操作<br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key <br>
     */
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    /**
     * Description: 删除key对应的set中指定的值<br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key key
     * @param value 要删除的值<br>
     */
    public void removeValueInSet(String key, String value) {
        redisTemplate.opsForSet().remove(key, value);
    }
    /**
     * Description: 从右侧拉取redis的list中的数据<br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key <br>
     * @return <br>
     */
    public String rightPopFromList(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }
    /**
     * Description: 判断redis中是否包含key<br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key key
     * @return <br>
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 操作hash结构：添加整个hash Description: <br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key key
     * @param field 属性key
     * @param value 属性value
     */
    public void hmset(String key, String field, String value) {
        Map<String, String> map = new HashMap();
        map.put(field, value);
        redisTemplate.opsForHash().putAll(key, map);
    }
    /**
     * 判断key的Hash结构中是否有输入的field Description: <br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key key
     * @param field 属性key
     * @return <br>
     */
    public Boolean hexsit(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }
    /**
     * 向已经存在的hash结构中添加或修改数据Description: <br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key key
     * @param field 属性key
     * @param value 属性value
     */
    public void hSet(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 获取hash中的所有键值对 Description: <br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key key
     * @return <br>
     */
    public Map<Object, Object> getMap(String key) {
        return redisTemplate.opsForHash().entries(key);

    }

    /**
     * hash实现加法 +1 Description: <br>
     *
     * @author xuxin<br>
     * @taskId <br>
     * @param key key
     * @param field 属性key <br>
     */
    public void hincrby(String key, String field) {
        redisTemplate.opsForHash().increment(key, field, 1);
    }
    public String get(String key){
      return  redisTemplate.opsForValue().get(key);
    }
    /**
     * set redisTemplate
     * @param redisTemplate The redisTemplate to set. <br>
     */
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }



}
