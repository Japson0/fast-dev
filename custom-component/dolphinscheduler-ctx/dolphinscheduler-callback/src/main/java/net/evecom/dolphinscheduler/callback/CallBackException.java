package net.evecom.dolphinscheduler.callback;

/**
 * <P><B>回调异常:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年08月24日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class CallBackException extends RuntimeException {

    public CallBackException() {
    }

    public CallBackException(String message) {
        super(message);
    }

    public CallBackException(String message, Throwable cause) {
        super(message, cause);
    }
}
