
package net.github.fastdev.mybatis.annotation;

/**
 * <P><B>加解密类型:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月12日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public enum EncryptType {

    /**
     * SM4,对称算法
     */
    SM4(true),

    /**
     * SM3,摘要算法
     */
    SM3(false);

    private final boolean canDecrypt;

    public boolean isCanDecrypt() {
        return canDecrypt;
    }


    EncryptType(boolean canDecrypt) {
        this.canDecrypt = canDecrypt;
    }
}
