package com.hhq.gateway.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

public class RedisCacheManager implements CacheManager {

    /**
     * 注入的redisTemplate
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * get redisTemplate
     *
     * @return Returns the redisTemplate.<br>
     */
    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * set redisTemplate
     *
     * @param redisTemplate The redisTemplate to set. <br>
     */
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <K, V> Cache<K, V> getCache(String name) {
        return new ShiroCache<>(name, (RedisTemplate<K, V>) redisTemplate);
    }

}