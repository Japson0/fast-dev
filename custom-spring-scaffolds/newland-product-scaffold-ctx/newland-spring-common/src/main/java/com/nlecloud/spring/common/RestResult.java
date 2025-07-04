package com.nlecloud.spring.common;

import com.nlecloud.spring.common.i18n.I18nUtils;
import net.github.fastdev.common.exception.CommonError;
import net.github.fastdev.common.exception.IErrorCode;
import net.github.fastdev.common.model.RestResponse;

import java.util.Locale;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月10日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class RestResult<T>  extends RestResponse<T> {



    private static final RestResult RENDER_SUCCESS=new RestResult(true,null,CommonError.SUCCEED.getCode());


    public RestResult() {
    }
    public RestResult(boolean success) {
        this(success, null);
    }

    public RestResult(boolean success, T data) {
        this(success, data, null);
    }

    public RestResult(boolean success, T data, String i18nKey,Object...args) {
        super(success,i18nKey,data,i18nKey==null?null: I18nUtils.getMessage(i18nKey,args));
    }

    public RestResult(boolean success, T data, String i18nKey, Locale locale,Object...args) {
        super(success,i18nKey,data,i18nKey==null?null: I18nUtils.getMessage(i18nKey,locale,args));
    }

    /**
     * 返回成功，默认 Revision Trail: (Date/Author/Description) 2019/5/9 Timer He CREATE
     *
     * @return
     * @author Timer He
     */
    public static RestResult renderSuccess() {
        return RENDER_SUCCESS;
    }


    /**
     * 返回成功，带数据 Revision Trail: (Date/Author/Description) 2019/5/9 Timer He CREATE
     *
     * @param data
     * @return
     * @author Timer He
     */
    public static <T> RestResult<T> renderSuccess(T data) {
        return new RestResult<>(true,data,CommonError.SUCCEED.getCode());
    }


    public static RestResult renderSuccess2Msg(String i18nKey) {
        return new RestResult(true, null, i18nKey);
    }



    /**
     * @param errorCode
     * @return
     */
    public static <T> RestResult<T> renderError(IErrorCode errorCode) {
        return new RestResult<>(false, null, errorCode.getCode());
    }

    /**
     * @param i18nKey
     * @return
     */
    public static <T> RestResult<T> renderError(String i18nKey) {
        return new RestResult<>(false, null, i18nKey);
    }

}
