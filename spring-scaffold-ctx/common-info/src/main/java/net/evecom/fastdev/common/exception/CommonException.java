package net.evecom.fastdev.common.exception;

import java.io.Serializable;

/**
 * 公共异常类。
 *
 * @author 创建人：jim
 * @version 版本号：V1.0
 */
public class CommonException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -422452041489241546L;
    /**
     * 异常代码。
     */
    private final String code;

    /**
     * 异常信息。
     */
    private final String msg;


    public CommonException(String msg) {
        this(null, CommonError.USER_RESOURCE_EXCEPTION.getCode(), msg);
    }


    public CommonException(String code, String msg) {
        this(null, code, msg);
    }

    public CommonException(Throwable throwable, String msg) {
        this(throwable, CommonError.USER_RESOURCE_EXCEPTION.getCode(), msg);
    }

    public CommonException(Throwable throwable, String code, String msg) {
        super(throwable);
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return msg;
    }

}
