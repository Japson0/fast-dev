
package net.github.fastdev.boot.handle;


import jakarta.servlet.http.HttpServletRequest;

/**
 * <P><B>判断是否需要脱敏的服务:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月16日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public interface WebTransSecurityServer {

    /**
     * 脱敏Key
     */
    String FUZZY_KEY = "FUZZY_KEY";

    /**
     * 加密的Key
     */
    String CRYPTO_KET = "CRYPTO_KET";

    /**
     * 是否需要脱敏，返回false标识不需要脱敏
     * RevisionTrail:(Date/Author/Description)
     * 2020年10月16日
     *
     * @author Japson Huang
     */
    boolean isFuzzy(HttpServletRequest httpServletRequest);

    /**
     * 获取密钥（用作加解密）
     */
    byte[] getCryptKey(HttpServletRequest httpServletRequest);
}
