package com.nlecloud.spring.scaffold.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P><B>注入机器人用户:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年05月06日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Target(ElementType.METHOD) // 注解作用在方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectRobot {

    boolean update() default false;

    long userId() default 0L;

    String username() default "";

    long tenantId() default 0L;


}
