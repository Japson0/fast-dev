/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.boot.handle;

import javax.servlet.http.HttpServletRequest;

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
