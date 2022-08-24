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

    /**
     * 异常信息
     */
    private Throwable throwable;


    public CommonException(String code, String msg, Object... data) {
        this.code = code;
        if (data != null && data.length > 0) {
            this.msg = String.format(msg, data);
        } else {
            this.msg = msg;
        }
    }

    public CommonException(Throwable throwable, String code, String msg, Object... data) {
        super(throwable);
        this.code = code;
        if (data != null && data.length > 0) {
            this.msg = String.format(msg, data);
        } else {
            this.msg = msg;
        }
    }


    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
