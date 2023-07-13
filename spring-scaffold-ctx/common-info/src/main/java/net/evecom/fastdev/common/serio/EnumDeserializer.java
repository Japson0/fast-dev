/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.common.serio;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import net.evecom.fastdev.common.model.ComEnum;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <P><B>反序列化工厂:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月03日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class EnumDeserializer extends DicDeserializerFormat<ComEnum> implements ContextualDeserializer {

    private static final Map<Class, EnumDeserializer> CACHE_MAP = new HashMap<>();
    private Class<ComEnum> targetEnum;

    public EnumDeserializer(Class<ComEnum> targetEnum) {
        this.targetEnum = targetEnum;
    }

    public EnumDeserializer() {
    }


    @Override
    public ComEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        Object value = getValue(p);
        if (value == null) return null;
        if (value == "" && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) {
            return null;
        }
        for (ComEnum c : targetEnum.getEnumConstants()) {
            if (value.equals(c.getValue().toString())) {
                return c;
            }
        }
        if (!ctxt.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
            return ctxt.reportInputMismatch(targetEnum,
                    "not one of the values accepted for Enum class: %s,it expects values [%s]", targetEnum.getSimpleName(), values()
            );
        }
        return null;
    }

    public String values() {
        ComEnum[] enums = targetEnum.getEnumConstants();
        StringBuilder str = new StringBuilder(enums.length * 4);

        for (int i = 0; i < enums.length; i++) {
            str.append(enums[i].getValue());
            if (i != enums.length - 1) {
                str.append(",");
            }
        }
        return str.toString();
    }


    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        if (property != null) {
            Class<?> rawClass = ctxt.getContextualType().getRawClass();
            if (CACHE_MAP.get(rawClass) == null) {
                synchronized (this) {
                    if (CACHE_MAP.get(rawClass) == null) {
                        CACHE_MAP.put(rawClass, new EnumDeserializer((Class<ComEnum>) rawClass));
                    }
                }
            }
            return CACHE_MAP.get(rawClass);
        }
        return new EnumDeserializer((Class<ComEnum>) ctxt.getContextualType().getRawClass());
    }
}
