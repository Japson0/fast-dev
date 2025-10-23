package net.github.fastdev.boot.validate.handle;

import cn.hutool.core.lang.Validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import net.github.fastdev.boot.validate.Phone;

/**
 * <P><B>手机号:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年10月23日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return Validator.isMobile(value);
    }

}
