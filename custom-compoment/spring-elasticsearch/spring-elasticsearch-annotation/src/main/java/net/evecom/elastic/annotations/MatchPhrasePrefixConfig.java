/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.elastic.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P><B>MatchPhrasePrefix:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年03月30日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MatchPhrasePrefixConfig {

    String analyzer() default "";

    int slop() default -1;

    int maxExpansions() default -1;
}
