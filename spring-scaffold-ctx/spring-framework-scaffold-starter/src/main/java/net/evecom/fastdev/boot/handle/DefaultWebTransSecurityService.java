package net.evecom.fastdev.boot.handle;

import javax.servlet.http.HttpServletRequest;

/**
 * <P><B>默认的脱敏判断服务:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月16日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class DefaultWebTransSecurityService implements WebTransSecurityServer {

    @Override
    public boolean isFuzzy(HttpServletRequest httpServletRequest) {
        Object deKey = httpServletRequest.getSession().getAttribute(WebTransSecurityServer.FUZZY_KEY);
        return deKey == null ? Boolean.FALSE : (Boolean) deKey;
    }

    @Override
    public byte[] getCryptKey(HttpServletRequest httpServletRequest) {
        Object deKey = httpServletRequest.getSession().getAttribute(WebTransSecurityServer.CRYPTO_KET);
        return deKey == null ? null : (byte[]) deKey;
    }


}
