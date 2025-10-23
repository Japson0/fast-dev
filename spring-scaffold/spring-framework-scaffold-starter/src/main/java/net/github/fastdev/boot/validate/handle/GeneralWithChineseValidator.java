package net.github.fastdev.boot.validate.handle;

import cn.hutool.core.lang.Validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import net.github.fastdev.boot.validate.GeneralWithChinese;
import net.github.fastdev.boot.validate.HttpUrl;

/**
 * <P><B>英文字母 、数字和下划线:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年10月23日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class GeneralWithChineseValidator implements ConstraintValidator<GeneralWithChinese, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // 如果允许 null，返回 true；否则返回 false
        }
        return Validator.isGeneral(value);
    }
}
