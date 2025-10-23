package net.github.fastdev.boot.validate.handle;

import cn.hutool.core.util.IdcardUtil;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import net.github.fastdev.boot.validate.IdCard;


/**
 * <P><B>身份证:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年10月23日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class IdCardValidator implements ConstraintValidator<IdCard, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return IdcardUtil.isValidCard(value);
    }
}
