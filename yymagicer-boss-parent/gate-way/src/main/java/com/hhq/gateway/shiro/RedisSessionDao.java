package com.hhq.gateway.shiro;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class RedisSessionDao extends EnterpriseCacheSessionDAO {
    // Session超时时间，单位为毫秒
    private long expireTime = 900000;

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisSessionDao() {
        super();
    }

    public RedisSessionDao(long expireTime, RedisTemplate redisTemplate) {
        super();
        this.expireTime = expireTime;
        this.redisTemplate = redisTemplate;
    }
    @Override// 加入session
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        redisTemplate.opsForValue().set(sessionId,session, expireTime, TimeUnit.MILLISECONDS);
        System.out.println("===============doCreate================"+sessionId);
        System.out.println("redis="+redisTemplate.opsForValue().get(sessionId));
        return sessionId;
    }
    @Override// 读取session
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("==============doReadSession================="+sessionId);
        if (sessionId == null) {
            return null;
        }
        System.out.println("==============doReadSession===redis=============="+redisTemplate.opsForValue().get(sessionId));
        return (Session) redisTemplate.opsForValue().get(sessionId);
    }
    @Override // 更新session
    public void update(Session session) throws UnknownSessionException {
        System.out.println("===============update================"+ JSONObject.toJSONString(session));
        if (session == null || session.getId() == null) {
            return;
        }
        session.setTimeout(expireTime);
        redisTemplate.opsForValue().set(session.getId(), session, expireTime, TimeUnit.MILLISECONDS);
    }

    @Override // 删除session
    public void delete(Session session) {
        System.out.println("===============delete================");
        if (null == session) {
            return;
        }
        redisTemplate.opsForValue().getOperations().delete(session.getId());
    }

    @Override// 获取活跃的session，可以用来统计在线人数，如果要实现这个功能，可以在将session加入redis时指定一个session前缀，统计的时候则使用keys("session-prefix*")的方式来模糊查找redis中所有的session集合
    public Collection<Session> getActiveSessions() {
        System.out.println("==============getActiveSessions=================");
        return redisTemplate.keys("*");
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
