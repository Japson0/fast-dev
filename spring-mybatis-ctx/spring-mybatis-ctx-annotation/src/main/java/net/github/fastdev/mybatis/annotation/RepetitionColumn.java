package net.github.fastdev.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P><B>重复字段值:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年05月19日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RepetitionColumn {


}
