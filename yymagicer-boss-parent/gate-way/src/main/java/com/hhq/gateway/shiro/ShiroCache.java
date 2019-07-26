package com.hhq.gateway.shiro;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * ShiroCache <Description> <br>
 * 
 * @author zhangshuxing<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2017年8月21日 <br>
 * @param <K> key
 * @param <V> value
 */
public class ShiroCache<K, V> implements Cache<K, V> {

    /**
     * redis前缀key
     */
    private static final String KEY_PREFIX = "hhq-shiro-cache:";

    /**
     * redis中存放的key
     */
    private String cacheKey;

    /**
     * redisTemplate
     */
    private RedisTemplate<K, V> redisTemplate;

    /**
     * 1s等于1000毫秒
     */
    private static final long MS = 1000L;

    /**
     * 构造函数
     * 
     * @param name name
     * @param redisTemplate redisTemplate
     */
    public ShiroCache(String name, RedisTemplate<K, V> redisTemplate) {
        this.cacheKey = KEY_PREFIX + name + ":";
        this.redisTemplate = redisTemplate;
    }

    @Override
    public V get(K key) {
        K cachedKey = getCachedKey(key);
        return redisTemplate.boundValueOps(cachedKey).get();
    }

    /**
     * 根据key获取value Description: <br>
     * 
     * @author zhangshuxing<br>
     * @taskId <br>
     * @param key key
     * @return 对应的value
     */
    private V getWithConcreteKey(K key) {
        return redisTemplate.boundValueOps(key).get();
    }

    /**
     * 获取缓存的value Description: <br>
     * 
     * @author minivision<br>
     * @taskId <br>
     * @param key key
     * @return <br>
     */
    @SuppressWarnings("unchecked")
    private K getCachedKey(K key) {
        return (K) (this.cacheKey + key);
    }

    @Override
    public V put(K key, V value) {
        V old = get(key);
        if (value instanceof Session) {
            Session session = (Session) value;
            redisTemplate.boundValueOps(getCachedKey(key)).set(value, session.getTimeout() / MS, TimeUnit.SECONDS);
        } else {
            redisTemplate.boundValueOps(getCachedKey(key)).set(value);
        }
        return old;
    }

    @Override
    public V remove(K key) {
        V old = get(key);
        redisTemplate.delete(getCachedKey(key));
        return old;
    }

    @Override
    public void clear() {

        redisTemplate.delete(keys());
    }

    @Override
    public int size() {
        return keys().size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keys() {
        return redisTemplate.keys(getCachedKey((K) "*"));
    }

    @Override
    public Collection<V> values() {
        Set<K> set = keys();
        List<V> list = new ArrayList<>();
        for (K s : set) {
            list.add(getWithConcreteKey(s));
        }
        return list;
    }

}
