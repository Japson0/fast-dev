
package net.github.fastdev.common.exception;

/**
 * <P><B>资源异常:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年12月08日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class ResourceException extends CommonException {

    private static final long serialVersionUID = -2883592666172747118L;

    public ResourceException(String msg) {
        this(null, CommonError.USER_RESOURCE_EXCEPTION.getCode(), msg);
    }

    public ResourceException(String code, String msg) {
        this(null, code, msg);
    }

    public ResourceException(Throwable throwable, String msg) {
        super(throwable, CommonError.USER_RESOURCE_EXCEPTION.getCode(), msg);
    }


    public ResourceException(Throwable throwable, String code, String msg) {
        super(throwable, code, msg);
    }

}
