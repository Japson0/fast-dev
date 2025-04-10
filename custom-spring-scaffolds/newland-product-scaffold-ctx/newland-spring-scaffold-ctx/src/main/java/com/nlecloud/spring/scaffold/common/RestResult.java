package com.nlecloud.spring.scaffold.common;

import com.nlecloud.spring.scaffold.utils.I18nUtils;
import io.swagger.annotations.ApiModelProperty;
import net.github.fastdev.common.exception.CommonError;
import net.github.fastdev.common.exception.IErrorCode;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月10日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class RestResult<T>  {


    /**
     * 请求是否成功
     */
    @ApiModelProperty(value = "是否成功")
    private  boolean result = true;
    /**
     * 成功时返回的数据，失败时返回具体的异常信息
     */
    @ApiModelProperty(value = "返回数据：可以是对象或集合")
    private  T data;

    private  String message;

    private static final RestResult RENDER_SUCCESS=new RestResult(true,null,CommonError.SUCCEED.getCode());


    public RestResult() {
    }
    public RestResult(boolean success) {
        this(success, null);
    }

    public RestResult(boolean success, T data) {
        this(success, data, null);
    }

    public RestResult(boolean success, T data, String i18nKey) {
        this.result = success;
        this.data = data;
        if(i18nKey!=null) {
            this.message = I18nUtils.getMessage(i18nKey,null);
        }
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

    public boolean isResult() {
        return result;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
