/*
 * Copyright (c) 2005, 2018, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.elastic.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P><B>索引注解:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年05月27日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ElasticQueryIndex {

    /**
     * 索引名字
     */
    String index();

    /**
     * 该索引名字是否是别名。
     */
    boolean isAlias() default false;

    /**
     * 返回的列
     */
    String[] source() default {};

    /**
     * 返回的列是否包含字段上的属性
     */
    boolean includeField() default false;
}
