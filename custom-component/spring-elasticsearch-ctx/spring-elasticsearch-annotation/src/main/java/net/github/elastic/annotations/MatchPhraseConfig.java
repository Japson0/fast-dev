

package net.github.elastic.annotations;

import net.github.elastic.enums.ZeroTermsQuery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P><B>MatchPhrase:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年03月29日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MatchPhraseConfig {
    String analyzer() default "";

    int slop() default -1;

    ZeroTermsQuery zeroTermsQuery() default ZeroTermsQuery.NONE;
}
