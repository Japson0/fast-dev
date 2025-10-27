package net.github.fastdev.boot.handle;

import javax.servlet.http.HttpServletRequest;

/**
 * <P><B>默认的脱敏判断服务:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月16日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class DefaultWebTransSecurityService implements WebTransSecurityServer {

    /**
     * 脱敏Key
     */
    String FUZZY_KEY = "FUZZY_KEY";

    /**
     * 加密的Key
     */
    String CRYPTO_KET = "CRYPTO_KET";

    @Override
    public boolean isFuzzy(HttpServletRequest httpServletRequest) {
        Object deKey = httpServletRequest.getSession().getAttribute(FUZZY_KEY);
        return deKey == null ? Boolean.FALSE : (Boolean) deKey;
    }

    @Override
    public byte[] getCryptKey(HttpServletRequest httpServletRequest) {
        Object deKey = httpServletRequest.getSession().getAttribute(CRYPTO_KET);
        return deKey == null ? null : (byte[]) deKey;
    }


}
