package com.necloud.spring.common.exception;

import com.necloud.spring.common.i18n.I18nUtils;
import net.github.fastdev.common.exception.CommonError;
import net.github.fastdev.common.exception.CommonException;

/**
 * <P><B>业务异常:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月11日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class BusinessException extends CommonException {


    public BusinessException(CommonError commonError) {
        this( commonError.getCode());
    }

    public BusinessException(Throwable throwable, CommonError commonError) {
        this(throwable, commonError.getCode());
    }

    public BusinessException(Throwable throwable, String code, CommonError commonError) {
        this(throwable, code, commonError.getCode(),null);
    }


    public BusinessException(String i18nKey,String...args) {
        this(null, i18nKey,args);
    }

    public BusinessException(Throwable throwable, String i18nKey,String...args) {
        this(throwable, null,i18nKey,args);
    }

    public BusinessException(Throwable throwable, String code, String i18nKey,String...args) {
        super(throwable, code, I18nUtils.getMessage(i18nKey,args));
    }
}
