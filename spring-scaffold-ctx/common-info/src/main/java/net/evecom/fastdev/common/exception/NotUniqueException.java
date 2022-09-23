package net.evecom.fastdev.common.exception;

/**
 * <P><B>唯一性异常:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年09月18日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class NotUniqueException extends CommonException {

    public NotUniqueException(String msg) {
        super(CommonError.USER_RESOURCE_EXCEPTION.getCode(), msg);
    }

    public NotUniqueException(String msg, Object... data) {
        super(CommonError.USER_RESOURCE_EXCEPTION.getCode(), msg, data);
    }

    public NotUniqueException(Throwable throwable, String msg, Object... data) {
        super(throwable, CommonError.USER_RESOURCE_EXCEPTION.getCode(), msg, data);
    }

}
