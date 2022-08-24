/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * <P><B>校验数据工具类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年12月07日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ValidateUtils {

    /**
     * 校验类
     */
    private final static Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> Set<ConstraintViolation<T>> validate(T t, Class<?>... classes) {
        return VALIDATOR.validate(t, classes);
    }

    public static <T> Set<ConstraintViolation<T>> validateProperty(T t, String s, Class<?>... classes) {
        return VALIDATOR.validateProperty(t, s, classes);
    }

    public static <T> Set<ConstraintViolation<T>> validateValue(Class<T> aClass, String s, Object o, Class<?>... classes) {
        return VALIDATOR.validateValue(aClass, s, o, classes);
    }
}
