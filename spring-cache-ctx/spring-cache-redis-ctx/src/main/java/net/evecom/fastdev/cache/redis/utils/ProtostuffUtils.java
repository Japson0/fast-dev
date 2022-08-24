/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.cache.redis.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Protostuff工具类
 * RevisionTrail:(Date/Author/Description)
 * 2021年08月16日
 *
 * @author Japson Huang
 */
public class ProtostuffUtils {


    /**
     * 序列化/反序列化包装类 Class 对象
     */
    private static final Class<ObjectWrapper> WRAPPER_CLASS = ObjectWrapper.class;

    /**
     * 序列化/反序列化包装类 Schema 对象
     */
    private static final Schema<ObjectWrapper> WRAPPER_SCHEMA = RuntimeSchema.createFrom(WRAPPER_CLASS);

    /**
     * 缓存对象及对象schema信息集合
     */
    private static final Map<Class<?>, Schema<?>> CACHE_SCHEMA = new ConcurrentHashMap<>();


    /**
     * 获取序列化对象类型的schema
     *
     * @param cls 序列化对象的class
     * @param <T> 序列化对象的类型
     * @return 序列化对象类型的schema
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <T> Schema<T> getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>) CACHE_SCHEMA.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls);
            CACHE_SCHEMA.put(cls, schema);
        }
        return schema;
    }

    /**
     * 序列化对象
     *
     * @param obj 需要序列化的对象
     * @param <T> 序列化对象的类型
     * @return 序列化后的二进制数组
     */
    @SuppressWarnings("unchecked")
    public static <T> byte[] serialize(T obj) {
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            if (obj.getClass() == WRAPPER_CLASS) {
                return ProtostuffIOUtil.toByteArray((ObjectWrapper) obj, WRAPPER_SCHEMA, buffer);
            }
            if (noSupport(clazz)) {
                return ProtostuffIOUtil.toByteArray(ObjectWrapper.builder(obj), WRAPPER_SCHEMA, buffer);
            } else {
                return ProtostuffIOUtil.toByteArray(obj, getSchema(clazz), buffer);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化对象
     *
     * @param data  需要反序列化的二进制数组
     * @param clazz 反序列化后的对象class
     * @param <T>   反序列化后的对象类型
     * @return 反序列化后的对象集合
     */
    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        try {
            if (clazz == WRAPPER_CLASS) {
                ObjectWrapper objectWrapper = new ObjectWrapper();
                ProtostuffIOUtil.mergeFrom(data, objectWrapper, WRAPPER_SCHEMA);
                return (T) objectWrapper;
            }
            if (noSupport(clazz)) {
                ObjectWrapper<T> wrapper = new ObjectWrapper<>();
                ProtostuffIOUtil.mergeFrom(data, wrapper, WRAPPER_SCHEMA);
                return wrapper.getData();
            } else {
                T message = clazz.newInstance();
                Schema<T> schema = getSchema(clazz);
                ProtostuffIOUtil.mergeFrom(data, message, schema);
                return message;
            }
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private static boolean noSupport(Class clazz) {
        return Iterable.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz) || Object.class == clazz;
    }

    /**
     * 包装类
     * RevisionTrail:(Date/Author/Description)
     * 2022年08月23日
     *
     * @author Japson Huang
     */
    static class ObjectWrapper<T> {

        /**
         * 实际值
         */
        private T data;

        public static <T> ObjectWrapper<T> builder(T data) {
            ObjectWrapper<T> wrapper = new ObjectWrapper<>();
            wrapper.setData(data);
            return wrapper;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

}
