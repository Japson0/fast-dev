package com.nlecloud.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年08月04日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})

public @interface ApiGroup {

    String value();
}
