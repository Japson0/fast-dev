package net.github.fastdev.boot.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import net.github.fastdev.boot.validate.handle.HttpUrlValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <P><B>HTTP_URL验证:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年10月23日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Documented
@Constraint(validatedBy = HttpUrlValidator.class) // 指定验证器
@Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface HttpUrl {

    String message() default "非法的http(s)地址";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
