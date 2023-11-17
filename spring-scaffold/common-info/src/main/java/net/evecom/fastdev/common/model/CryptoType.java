/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.common.model;

/**
 * <P><B>加解密类型:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年05月13日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public enum CryptoType {

    /**
     * 不加密
     */
    NONE,
    /**
     * SM4国密
     */
    SM4,
    /**
     * AES对称加密
     */
    AES;

}
