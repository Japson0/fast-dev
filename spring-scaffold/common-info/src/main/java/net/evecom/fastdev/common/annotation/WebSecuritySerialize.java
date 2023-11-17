package net.evecom.fastdev.common.annotation;

import net.evecom.fastdev.common.model.CryptoType;
import net.evecom.fastdev.common.model.DistortionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P><B>JackSon序列化注解:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月15日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface WebSecuritySerialize {

    /**
     * 脱敏规则
     */
    DistortionType distortion() default DistortionType.NONE;

    /**
     * 加解密规则
     */
    CryptoType crypto() default CryptoType.NONE;


}
