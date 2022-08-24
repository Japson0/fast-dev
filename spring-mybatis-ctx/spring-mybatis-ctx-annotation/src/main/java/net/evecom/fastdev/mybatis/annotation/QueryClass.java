package net.evecom.fastdev.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P><B>查询类注解:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年09月11日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface QueryClass {

    /**
     * 字段别名
     */
    String alias() default "";

    /**
     * 是否是ORACLE查询
     *
     * @return
     */
    boolean isOracle() default false;
}
