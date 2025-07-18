package com.nlecloud.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P><B>权限控制:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年03月27日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface ApiName {


    /**
     * 接口名称
     */
    String value();


}
