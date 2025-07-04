package com.nlecloud.spring.common.i18n;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * <P><B>国际化工具类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月10日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class I18nUtils {

    private final static MessageSource messageSource;


    static {
        messageSource= SpringUtil.getBean(MessageSource.class);
    }

    public static String getMessage(String code, Object[] args) {

        return getMessage(code,LocaleContextHolder.getLocale(),args);
    }

    public static String getMessage(String code,Locale locale, Object[] args) {

        return  messageSource.getMessage(code, args, locale==null?Locale.SIMPLIFIED_CHINESE:locale);
    }
}
