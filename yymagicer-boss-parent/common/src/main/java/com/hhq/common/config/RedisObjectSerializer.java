package com.hhq.common.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class RedisObjectSerializer  implements RedisSerializer<Object> {

    /**
     * 序列化器
     */
    private Converter<Object, byte[]> serializer = new SerializingConverter();

    /**
     * 反序列化器
     */
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    /**
     * 空表示
     */
    private static final byte[] EMPTY_ARRAY = new byte[0];

    @Override
    public Object deserialize(byte[] bytes) {
        if (isEmpty(bytes)) {
            return null;
        }
        try {
            return deserializer.convert(bytes);
        } catch (Exception e) {
            throw new SerializationException("can not deserialize", e);
        }
    }
    private boolean isEmpty(byte[] bytes) {
        return bytes == null || bytes.length == 0;
    }
    @Override
    public byte[] serialize(Object obj) {
        if (null == obj) {
            return EMPTY_ARRAY;
        }
        try {
            return serializer.convert(obj);
        } catch (Exception e) {
            return EMPTY_ARRAY;
        }

    }

}