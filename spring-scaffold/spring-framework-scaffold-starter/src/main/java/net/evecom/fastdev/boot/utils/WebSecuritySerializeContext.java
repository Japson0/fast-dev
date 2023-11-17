package net.evecom.fastdev.boot.utils;

/**
 * <P><B>网络传输安全操作上下文:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月15日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class WebSecuritySerializeContext {

    /**
     * 脱敏key
     */
    public static final String FUZZY_KEY = "distortionKey";

    /**
     * 加密的key
     */
    public static final String CRYPTO_KEY = "cryptoKey";

    /**
     * 脱敏线程本地变量
     */
    private final static ThreadLocal<Boolean> FUZZY_LOCAL = ThreadLocal.withInitial(() -> Boolean.FALSE);

    /**
     * 加解密密钥本地变量
     */
    private final static ThreadLocal<byte[]> CRYPTO_LOCAL = new ThreadLocal<>();

    /**
     * 是否脱敏
     * RevisionTrail:(Date/Author/Description)
     * 2020年10月16日
     *
     * @author Japson Huang
     */
    public static boolean isFuzzy() {
        return FUZZY_LOCAL.get();
    }

    public static void fuzzy() {
        FUZZY_LOCAL.set(Boolean.TRUE);
    }

    /**
     * 无需脱敏
     * RevisionTrail:(Date/Author/Description)
     * 2020年10月16日
     *
     * @author Japson Huang
     */
    public static void unFuzzy() {
        FUZZY_LOCAL.set(Boolean.FALSE);
    }

    public static byte[] getCryptKey() {
        return CRYPTO_LOCAL.get();
    }

    public static void setCryptoKey(byte[] cryptoKey) {
        CRYPTO_LOCAL.set(cryptoKey);
    }

    /**
     * 清除上下文内容
     * RevisionTrail:(Date/Author/Description)
     * 2020年10月16日
     *
     * @author Japson Huang
     */
    public static void reset() {
        FUZZY_LOCAL.remove();
        CRYPTO_LOCAL.remove();
    }
}
