/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P><B>where条件注解:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年07月07日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface QueryField {

    /**
     * 数据库字段名，如果为空则取参数名的驼峰映射
     */
    String value() default "";

    /**
     * 是否存在
     */
    boolean exists() default true;

    /**
     * 操作符，如LIKE、EQ之类的
     */
    ConditionOperation condition() default ConditionOperation.EQ;

    /**
     * 字段别名，比如A.NAME
     */
    String alias() default "";

    boolean ignoreClassAlias() default false;
}
