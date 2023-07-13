package net.evecom.fastdev.mybatis.encrypt;

import net.evecom.fastdev.mybatis.config.MybatisCtxProperties;
import net.evecom.fastdev.mybatis.util.Sm4Util;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月13日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class EncryptCertificate {

    private final byte[] sm4Key;

    public EncryptCertificate(MybatisCtxProperties.Encrcypt encrcypt) {
        String sms4Key = encrcypt.getSm4Key();
        if (sms4Key == null || sms4Key.length() != 32) {
            throw new IllegalArgumentException("key of length in SM4 must be equal to 32");
        }
        this.sm4Key = Sm4Util.hexStringToByteArray(sms4Key);
    }

    public byte[] getSm4Key() {
        return sm4Key;
    }


    public String sm4Encrypt(String data) {
        try {
            return Sm4Util.encryptEcb(sm4Key, data);
        } catch (Exception e) {
            throw new RuntimeException("加密失败" + e.getMessage());
        }
    }

    public String sm4Decrypt(String data) {
        try {
            return Sm4Util.decryptEcb(sm4Key, data);
        } catch (Exception e) {
            throw new RuntimeException("解密失败" + e.getMessage());
        }
    }
}
