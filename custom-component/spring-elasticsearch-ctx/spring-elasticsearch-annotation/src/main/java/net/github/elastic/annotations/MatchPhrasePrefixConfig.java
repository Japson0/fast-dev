

package net.github.elastic.annotations;


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
