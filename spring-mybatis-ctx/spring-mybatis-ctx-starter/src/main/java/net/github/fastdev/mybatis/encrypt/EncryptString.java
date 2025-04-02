package net.github.fastdev.mybatis.encrypt;

import net.github.fastdev.mybatis.annotation.EncryptType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <P><B>加密类型:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月12日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
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
            return encryptCertificate.encrypt(encryptType, object);
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
