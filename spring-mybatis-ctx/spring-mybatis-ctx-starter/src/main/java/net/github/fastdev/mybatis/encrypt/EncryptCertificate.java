package net.github.fastdev.mybatis.encrypt;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.symmetric.SM4;
import net.github.fastdev.mybatis.annotation.EncryptType;
import net.github.fastdev.mybatis.config.MybatisCtxProperties;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月13日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class EncryptCertificate {


    private SM4 sm4;


    public EncryptCertificate(MybatisCtxProperties.Encrcypt encrcypt) {
        String sms4Key = encrcypt.getSm4Key();
        if (sms4Key == null || sms4Key.length() != 32) {
            throw new IllegalArgumentException("key of length in SM4 must be equal to 32");
        }
        this.sm4 = new SM4(HexUtil.decodeHex(sms4Key));
    }

    public String encrypt(EncryptType encryptType, String data) {
        try {
            switch (encryptType) {
                case SM4:  return sm4.encryptHex(data);
                default: throw new IllegalArgumentException("Unsupported encrypt type: " + encryptType);
            }
        } catch (Exception e) {
            throw new RuntimeException("加密失败" + e.getMessage());
        }
    }



    public String decrypt(EncryptType encryptType,String data) {
        try {
            switch (encryptType) {
                case SM4:  return sm4.decryptStr(data);
                default: throw new IllegalArgumentException("Unsupported decrypt type: " + encryptType);
            }
        } catch (Exception e) {
            throw new RuntimeException("解密失败" + e.getMessage());
        }
    }
}
