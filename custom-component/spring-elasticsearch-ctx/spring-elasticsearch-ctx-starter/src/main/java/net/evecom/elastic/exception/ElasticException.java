package net.evecom.elastic.exception;

/**
 * <P><B>elastic异常:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年01月05日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ElasticException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 8391079818080596541L;

    public ElasticException() {
    }

    public ElasticException(String message) {
        super(message);
    }

    public ElasticException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElasticException(Throwable cause) {
        super(cause);
    }

    public ElasticException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
