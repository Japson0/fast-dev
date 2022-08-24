package net.evecom.fastdev.mybatis.encrypt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P><B>跳过加密注解:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月15日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
public @interface SkinEncrypt {

}
