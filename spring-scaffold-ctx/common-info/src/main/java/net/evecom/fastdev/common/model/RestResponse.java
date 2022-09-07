/*
 * Copyright (c) 2005, 2019, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.fastdev.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.evecom.fastdev.common.exception.CommonError;
import net.evecom.fastdev.common.exception.IErrorCode;
import net.evecom.fastdev.common.exception.ResourceNotFoundException;

/**
 * <p>
 * <B>Description: </B> RESR风格的请求结果数据结构
 * </P>
 * Revision Trail: (Date/Author/Description) 2019/3/20 Timer He CREATE
 *
 * @author Timer He
 * @version 1.0
 */
@ApiModel("返回参数")
public class RestResponse<T> {
    /**
     * 请求是否成功
     */
    @ApiModelProperty(value = "是否成功")
    private boolean result;
    /**
     * 成功或者失败的code错误码
     */
    @ApiModelProperty(value = "成功或者失败的应答码", example = "00000:正确，00001，错误，99999：系统异常")
    private String code;
    /**
     * 成功时返回的数据，失败时返回具体的异常信息
     */
    @ApiModelProperty(value = "返回数据：可以是对象或集合")
    private T data;
    /**
     * 请求失败返回的提示信息，给前端进行页面展示的信息
     */
    @ApiModelProperty(value = "返回信息描述")
    private String message;
    /**
     * 成功
     */
    private final static RestResponse RENDER_SUCCESS = new RestResponse(true, CommonError.SUCCEED.getCode());


    public RestResponse() {
    }

    public RestResponse(boolean success, String code) {
        this(success, code, null);
    }

    public RestResponse(boolean success, String code, T data) {
        this(success, code, data, null);
    }

    public RestResponse(boolean success, String code, T data, String errorMessage) {
        this.result = success;
        this.code = code;
        this.data = data;
        this.message = errorMessage;
    }

    /**
     * 返回成功，默认 Revision Trail: (Date/Author/Description) 2019/5/9 Timer He CREATE
     *
     * @return
     * @author Timer He
     */
    public static RestResponse renderSuccess() {
        return RENDER_SUCCESS;
    }


    /**
     * 返回成功，带数据 Revision Trail: (Date/Author/Description) 2019/5/9 Timer He CREATE
     *
     * @param data
     * @return
     * @author Timer He
     */
    public static <T> RestResponse<T> renderSuccess(T data) {
        return new RestResponse<>(true, CommonError.SUCCEED.getCode(), data, null);
    }

    public static RestResponse renderSuccess(String message) {
        return new RestResponse(true, CommonError.SUCCEED.getCode(), null, message);
    }

    /**
     * @param errorCode
     * @return
     */
    public static <T> RestResponse<T> renderError(IErrorCode errorCode) {
        return new RestResponse<>(false, errorCode.getCode(), null, errorCode.getMsg());
    }

    /**
     * 返回错误信息，自定义错误码 Revision Trail: (Date/Author/Description) 2019/5/9 Timer He
     * CREATE
     *
     * @param errorMessage
     * @param errorCode
     * @return
     * @author Timer He
     */
    public static <T> RestResponse<T> renderError(String errorCode, String errorMessage) {
        return new RestResponse<>(false, errorCode, null, errorMessage);
    }

    /**
     * 返回错误信息，自定义错误码 Revision Trail: (Date/Author/Description) 2019/5/9 Timer He
     * CREATE
     *
     * @param errorMessage
     * @return
     * @author Timer He
     */
    public static <T> RestResponse<T> renderError(String errorMessage) {
        return renderError(CommonError.USER_RESOURCE_EXCEPTION.getCode(), errorMessage);
    }

    /**
     * 用户资源异常
     * RevisionTrail:(Date/Author/Description)
     * 2021年07月26日
     *
     * @author Japson Huang
     */
    public static RestResponse renderUserResourceError() {
        return RestResponse.renderError(CommonError.USER_RESOURCE_EXCEPTION);
    }

    /**
     * 用户资源异常
     * RevisionTrail:(Date/Author/Description)
     * 2021年07月26日
     *
     * @author Japson Huang
     */
    public static RestResponse renderUserResourceError(String message) {
        return RestResponse.renderError(CommonError.USER_RESOURCE_EXCEPTION).setMessage(message);
    }

    /**
     * update and delete for operating resource nums;
     * 资源修改断言，一般用作U(update)、D(delete)资源操作的数量
     * 一般对于update来说，如果更新操作的数量位0，说明没有这个资源
     * 同理delete
     * RevisionTrail:(Date/Author/Description)
     * 2021年07月26日
     *
     * @author Japson Huang
     */
    public static RestResponse UD4ResCount(int resourceCount) {
        if (resourceCount == 0) {
            throw new ResourceNotFoundException();
        }
        return RestResponse.renderSuccess();
    }

    public boolean isResult() {
        return result;
    }

    public RestResponse<T> setResult(boolean result) {
        this.result = result;
        return this;
    }

    public String getCode() {
        return code;
    }

    public RestResponse<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public RestResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RestResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "RestResponse{" + "success=" + result + ", code='" + code + '\'' + ", data=" + data + ", errorMessage="
                + message + '}';
    }
}
