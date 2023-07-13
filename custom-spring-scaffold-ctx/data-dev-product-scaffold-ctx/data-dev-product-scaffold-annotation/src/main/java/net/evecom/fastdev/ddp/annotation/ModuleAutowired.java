package net.evecom.fastdev.ddp.annotation;

import java.lang.annotation.*;

/**
 * <P><B>模块注入注解:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月26日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModuleAutowired {
}
