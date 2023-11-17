package net.evecom.dolphinscheduler.exception;

/**
 * <P><B>任务异常:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年07月24日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class JobException extends RuntimeException {

    public JobException(String message) {
        super(message);
    }

    public JobException(String message, Throwable cause) {
        super(message, cause);
    }
}
