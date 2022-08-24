/*
 * Copyright (c) 2005, 2019, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.serio;

import net.evecom.fastdev.common.model.ComEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用枚举转换工厂
 *
 * @author Frode Fan
 * @version 1.0
 */
public class EnumConverterFactory implements ConverterFactory<String, ComEnum> {

    /**
     * 缓存
     *
     * @author Japson Huang
     */
    private static final Map<Class, StringToEnum> ENUM_CONVER_MAP = new HashMap<>();

    @Override
    public <T extends ComEnum> Converter<String, T> getConverter(Class<T> aClass) {
        StringToEnum stringToEnum = ENUM_CONVER_MAP.get(aClass);
        if (stringToEnum == null) {
            synchronized (this) {
                if (stringToEnum == null) {
                    stringToEnum = new StringToEnum<>(aClass);
                    ENUM_CONVER_MAP.put(aClass, stringToEnum);
                }
            }
        }
        return stringToEnum;
    }

    /**
     * 字符串转枚举转换器
     *
     * @param <T> extends BaseEnum
     * @author Frode Fan
     * @version 1.0
     */
    private static class StringToEnum<T extends ComEnum> implements Converter<String, T> {
        /**
         * 当前枚举类型
         */
        private final Class<T> enumType;

        /**
         * Constructor
         *
         * @param enumType 枚举类型
         */
        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String s) {
            if (StringUtils.isEmpty(s)) {
                return null;
            }
            for (T t : enumType.getEnumConstants()) {
                if (s.equals(t.getValue().toString())) {
                    return t;
                }
            }
            return null;
        }
    }
}
