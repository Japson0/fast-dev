/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.cache.redis;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * <P><B>ProtoBuf序列化:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年08月14日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ProtoBufSerializationRedisSerializer implements RedisSerializer<Object> {
    /**
     * schema
     */
    private static final Schema<ObjectWrapper> schema = RuntimeSchema.getSchema(ObjectWrapper.class);

    /**
     * 默认
     */
    private final static ProtoBufSerializationRedisSerializer DEFAULT = new ProtoBufSerializationRedisSerializer();

    private ProtoBufSerializationRedisSerializer() {
    }

    public static ProtoBufSerializationRedisSerializer getInstance() {
        return DEFAULT;
    }

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        LinkedBuffer linkedBuffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            return ProtostuffIOUtil.toByteArray(new ObjectWrapper(t), schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        } finally {
            linkedBuffer.clear();
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ObjectWrapper objectWrapper = new ObjectWrapper();
        ProtostuffIOUtil.mergeFrom(bytes, objectWrapper, schema);
        return objectWrapper.getObject();
    }

    static class ObjectWrapper {
        /**
         * 实际类
         */
        private Object object;

        ObjectWrapper() {
        }

        ObjectWrapper(Object object) {
            this.object = object;
        }

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }
    }
}
