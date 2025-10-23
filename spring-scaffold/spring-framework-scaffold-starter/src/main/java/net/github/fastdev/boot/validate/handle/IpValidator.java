package net.github.fastdev.boot.validate.handle;

import cn.hutool.core.lang.Validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import net.github.fastdev.boot.validate.IdCard;

/**
 * <P><B>Ip校验器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年10月23日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class IpValidator implements ConstraintValidator<IdCard, String> {


    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return Validator.isIpv4(value)||Validator.isIpv6(value);
    }
}
