package net.github.fastdev.boot.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import net.github.fastdev.boot.validate.handle.GeneralWithChineseValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <P><B>英文字母、中文 、数字和下划线:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年10月23日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Constraint(validatedBy = GeneralWithChineseValidator.class) // 指定验证器
@Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface GeneralWithChinese {

    String message() default "只允许英文字母 、中文、数字和下划线";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}