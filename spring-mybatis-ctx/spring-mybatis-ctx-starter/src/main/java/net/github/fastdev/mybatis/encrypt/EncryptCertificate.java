package net.github.fastdev.mybatis.encrypt;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.digest.SM3;
import cn.hutool.crypto.symmetric.SM4;
import net.github.fastdev.mybatis.annotation.EncryptType;
import net.github.fastdev.mybatis.MybatisCtxProperties;

import java.util.Optional;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月13日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class EncryptCertificate {


    private Optional<SM4> sm4;

    private Optional<SM3> sm3;


    public EncryptCertificate(MybatisCtxProperties.Encrcypt encrcypt) {
        String sms4Key = encrcypt.getSm4Key();
        if (sms4Key != null) {
            if(sms4Key.length() != 32){
                throw new IllegalArgumentException("key of length in SM4 must be equal to 32");
            }
            this.sm4 = Optional.of(new SM4(HexUtil.decodeHex(sms4Key)));

        }

        String sm3key = encrcypt.getSm3key();
        if (sm3key != null) {
            if(sm3key.length() != 24){
                throw new IllegalArgumentException("key of length in SM3 must be equal to 24");
            }
            sm3=Optional.of(new SM3(HexUtil.decodeHex(sm3key)));
        }
        if(!sm3.isPresent()|| !sm4.isPresent()){
            throw new IllegalArgumentException("SM3 or SM4 must be present");
        }
    }

    public String encrypt(EncryptType encryptType, String data) {
        try {
            switch (encryptType) {
                case SM4:  return sm4.isPresent()?sm4.get().encryptHex(data):data;
                case SM3:   return sm3.isPresent()?sm3.get().digestHex(data):data;
                default: throw new IllegalArgumentException("Unsupported encrypt type: " + encryptType);
            }
        } catch (Exception e) {
            throw new RuntimeException("加密失败" + e.getMessage());
        }
    }



    public String decrypt(EncryptType encryptType,String data) {
        try {
            if(encryptType.isCanDecrypt()) {
                switch (encryptType) {
                    case SM4:
                        return sm4.isPresent()?sm4.get().decryptStr(data):data;
                    default:
                        throw new IllegalArgumentException("Unsupported decrypt type: " + encryptType);
                }
            }
            return data;
        } catch (Exception e) {
            throw new RuntimeException("解密失败" + e.getMessage());
        }
    }
}
