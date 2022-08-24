/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.serio;

import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import net.evecom.fastdev.boot.handle.DictionaryService;
import net.evecom.fastdev.common.annotation.Dictionary;
import net.evecom.fastdev.common.serio.DicSerializerFormat;
import org.springframework.beans.BeansException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <P><B>字典表注解:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月13日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class DictionarySerializer extends DicSerializerFormat<String> implements ContextualSerializer {
    /**
     * 缓存
     */
    public static final Map<String, DictionarySerializer> CACHE_MAP = new HashMap<>();
    /**
     * 字典服务层
     */
    private static DictionaryService dictionaryService;

    /**
     * 无字典表服务序列化器
     */
    private final static DictionarySerializer NULL_SERVICE_SERIALIZE = new DictionarySerializer() {
        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) {
                write(gen, value, value);
            }
        }
    };

    static {
        try {
            dictionaryService = SpringUtil.getBean(DictionaryService.class);
        } catch (BeansException ignore) {
            dictionaryService = null;
        }
    }

    /**
     * 字典大类
     */
    private String typeCode;

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        if (value != null) {
            String dicStr = dictionaryService.getDicStr(typeCode, value);
            write(gen, dicStr == null ? value : dicStr, value);
        }
    }


    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        if (property != null && dictionaryService != null) {
            Dictionary dictionary = property.getAnnotation(Dictionary.class);
            if (dictionary != null) {
                String typeCode = dictionary.value();
                if (CACHE_MAP.get(typeCode) == null) {
                    synchronized (CACHE_MAP) {
                        if (CACHE_MAP.get(typeCode) == null) {
                            DictionarySerializer dictionarySerialize = new DictionarySerializer();
                            dictionarySerialize.typeCode = typeCode;
                            CACHE_MAP.put(typeCode, dictionarySerialize);
                        }
                    }
                }
                return CACHE_MAP.get(typeCode);
            }
        }
        return NULL_SERVICE_SERIALIZE;
    }
}
