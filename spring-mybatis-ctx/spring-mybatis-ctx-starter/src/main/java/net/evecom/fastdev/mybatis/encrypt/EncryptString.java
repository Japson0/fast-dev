package net.evecom.fastdev.mybatis.encrypt;

import net.evecom.fastdev.mybatis.annotation.EncryptType;
import net.evecom.fastdev.mybatis.util.Sm4Util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <P><B>加密类型:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月12日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class EncryptString implements EncryptInterface<String> {

    private static Map<EncryptType, EncryptString> cacheEncryptString = new ConcurrentHashMap<>(EncryptType.values().length);

    /**
     * 加密类型
     */
    private EncryptType encryptType;

    private EncryptString(EncryptType encryptType) {
        this.encryptType = encryptType;
    }

    public static EncryptString generator(EncryptType encryptType) {
        EncryptString encryptString = cacheEncryptString.get(encryptType);
        if (encryptString == null) {
            encryptString = new EncryptString(encryptType);
            cacheEncryptString.put(encryptType, encryptString);
        }
        return encryptString;
    }

    @Override
    public String encrypt(String object, EncryptCertificate encryptCertificate) throws Exception {
        if (object != null) {
            if (encryptType == EncryptType.SM4) {
                return Sm4Util.encryptEcb(encryptCertificate.getSm4Key(), object);
            }
        }
        return null;
    }

    @Override
    public String decrypt(String object, EncryptCertificate encryptCertificate) {
        return object;
    }

    @Override
    public Class getSourceClass() {
        return String.class;
    }


}
