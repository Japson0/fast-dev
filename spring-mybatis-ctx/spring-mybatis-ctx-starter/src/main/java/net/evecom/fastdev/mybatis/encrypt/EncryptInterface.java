package net.evecom.fastdev.mybatis.encrypt;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月12日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public interface EncryptInterface<T> {


    T encrypt(T object, EncryptCertificate encryptCertificate) throws Exception;

    T decrypt(T object, EncryptCertificate encryptCertificate) throws Exception;

    Class getSourceClass();
}
