/*
 * Copyright (c) 2005, 2018, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.elastic.annotations;

import net.evecom.elastic.enums.ElasticBoolType;
import net.evecom.elastic.enums.ElasticOperator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P><B>Elastics转换注解:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年05月23日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticQueryField {

    /**
     * 操作符，如EQ,GTE等
     *
     * @return
     */
    ElasticOperator operator() default ElasticOperator.TERM;


    /**
     * 属于哪种查询组，如MUST,SHOULD,MUST_NOT
     *
     * @return
     */
    ElasticBoolType boolType() default ElasticBoolType.MUST;


    /**
     * 名称
     */
    String name();

    /**
     * 查询条件顺序，越低越排前，做优化用
     */
    int order() default 99;

    /**
     * 权重
     */
    float boost() default 1;
}
