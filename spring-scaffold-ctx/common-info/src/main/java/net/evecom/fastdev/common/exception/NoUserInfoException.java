package net.evecom.fastdev.common.exception;

/**
 * <P><B>无用户信息异常:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月25日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class NoUserInfoException extends ResourceNotFoundException {

    /**
     * 单例
     */
    private static final NoUserInfoException INSTANCE = new NoUserInfoException();

    private NoUserInfoException() {
        super("用户信息");
    }

    public static NoUserInfoException getInstance() {
        return INSTANCE;
    }
}
