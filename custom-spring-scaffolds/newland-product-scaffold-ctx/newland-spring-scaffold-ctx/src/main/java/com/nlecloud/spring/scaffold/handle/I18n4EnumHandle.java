package com.nlecloud.spring.scaffold.handle;

import com.nlecloud.spring.annotation.enums.I18nDisplay;
import com.nlecloud.spring.common.i18n.I18nUtils;
import net.github.fastdev.boot.handle.ComEnumDisplayHandle;
import net.github.fastdev.common.model.ComEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

/**
 * <P><B>枚举持久化类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class I18n4EnumHandle implements ComEnumDisplayHandle {

    private static final Logger LOGGER= LoggerFactory.getLogger(I18n4EnumHandle.class);

    @Override
    public String getDisplay(ComEnum comEnum) {
        if (comEnum == null) {
            return null;
        }

        // 1. 获取注解（带缓存）
        I18nDisplay annotation = AnnotationUtils.findAnnotation(
                comEnum.getClass(), I18nDisplay.class);
        // 2. 处理国际化逻辑
        if (annotation != null) {
            String i18nKey = StringUtils.hasText(annotation.value())
                    ? annotation.value()
                    : comEnum.getClass().getName();

            try {
                String displayValue = I18nUtils.getMessage(i18nKey+"."+comEnum.getValue(), null);
                if (displayValue != null) {
                    return displayValue;
                }
            } catch (Exception e) {
                // 国际化失败时回退到默认实现
                LOGGER.warn("Failed to get i18n message for key: {}", i18nKey, e);
            }
        }

        // 3. 回退到默认实现
        return comEnum.getDisplay();
    }

}
