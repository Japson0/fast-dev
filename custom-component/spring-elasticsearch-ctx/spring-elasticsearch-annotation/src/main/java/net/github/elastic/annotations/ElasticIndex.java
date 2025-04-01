

package net.github.elastic.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P><B>ES索引名称:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年01月19日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticIndex {

    String value();
}
