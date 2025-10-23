package net.github.fastdev.boot.validate.handle;

import cn.hutool.core.lang.Validator;
import net.github.fastdev.boot.validate.HttpUrl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * <P><B>http校验:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年10月23日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class HttpUrlValidator implements ConstraintValidator<HttpUrl, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // 如果允许 null，返回 true；否则返回 false
        }
        return Validator.isUrl(value);
    }
}
