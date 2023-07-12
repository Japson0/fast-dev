/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.cache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * <P><B>key生成策略:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年06月19日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class SimplateKeyGenerator implements KeyGenerator {

    /**
     * log
     */
    private final Logger LOGGER = LoggerFactory.getLogger(SimplateKeyGenerator.class);

    /**
     * 空密钥占位符
     */
    private final int NULL_PARAM_KEY = 53;

    @Override
    public Object generate(Object o, Method method, Object... objects) {

        StringBuilder key = new StringBuilder(AopUtils.getTargetClass(o).getName())
                .append("::").append(method.getName());
        for (Object param : objects) {
            if (param == null) {
                key.append(NULL_PARAM_KEY);
            } else if (ClassUtils.isPrimitiveArray(param.getClass())) {
                //基础类型的数组
                int length = Array.getLength(param);
                for (int i = 0; i < length; i++) {
                    key.append(Array.get(param, i)).append(",");
                }
            } else if (ClassUtils.isPrimitiveOrWrapper(param.getClass()) || param instanceof String) {
                //包装类或String
                key.append(param);
            } else if (param instanceof Map) {
                Set<Map.Entry> entry = ((Map) param).entrySet();
                entry.forEach(e -> {
                    this.addParams(key, e.getKey());
                    key.append(":");
                    this.addParams(key, e.getValue());
                });
            } else {
                key.append(param.hashCode());
            }
        }
        return key.toString();
    }

    private void addParams(StringBuilder key, Object o) {
        if (o == null) {
            key.append(NULL_PARAM_KEY);
        }
        if (ClassUtils.isPrimitiveOrWrapper(o.getClass()) || o instanceof String) {
            key.append(o);
        } else {
            key.append(o.hashCode());
        }
    }
}
