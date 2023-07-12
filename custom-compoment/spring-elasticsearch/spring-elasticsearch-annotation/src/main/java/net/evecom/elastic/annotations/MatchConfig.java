/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.elastic.annotations;

import net.evecom.elastic.enums.Operator;
import net.evecom.elastic.enums.ZeroTermsQuery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P><B>match配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年03月29日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MatchConfig {
    Operator operator() default Operator.OR;

    String analyzer() default "";

    int prefixLength() default -1;

    int maxExpansions() default -1;

    String minimumShouldMatch() default "";

    boolean fuzzyTranspositions() default true;

    String fuzzyRewrite() default "";

    boolean lenient() default false;

    ZeroTermsQuery zeroTermsQuery() default ZeroTermsQuery.NONE;


    boolean autoGenerateSynonymsPhraseQuery() default true;

    String fuzziness() default "NONE";
}
