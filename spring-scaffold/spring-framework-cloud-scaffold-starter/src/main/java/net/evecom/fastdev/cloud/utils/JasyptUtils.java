/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.cloud.utils;


import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;


/**
 * jasypt加解密工具类
 * RevisionTrail:(Date/Author/Description)
 * 2021年10月06日
 *
 * @author Japson Huang
 */
public class JasyptUtils {


    /**
     * Jasypt生成加密结果
     *
     * @param password 配置文件中设定的加密密码 jasypt.encryptor.password
     * @param value    待加密值
     * @return
     */

    public static String encryptPwd(String password, String value) {

        PooledPBEStringEncryptor encryptOr = new PooledPBEStringEncryptor();

        encryptOr.setConfig(cryptOr(password));

        String result = encryptOr.encrypt(value);

        return result;

    }


    /**
     * 解密
     *
     * @param password 配置文件中设定的加密密码 jasypt.encryptor.password
     * @param value    待解密密文
     * @return
     */

    public static String decryptPwd(String password, String value) {

        PooledPBEStringEncryptor encryptOr = new PooledPBEStringEncryptor();

        encryptOr.setConfig(cryptOr(password));

        String result = encryptOr.decrypt(value);

        return result;

    }

    /**
     * 默认配置
     * RevisionTrail:(Date/Author/Description)
     * 2021年10月06日
     *
     * @author Japson Huang
     */
    public static SimpleStringPBEConfig cryptOr(String password) {

        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(password);

        config.setAlgorithm(StandardPBEByteEncryptor.DEFAULT_ALGORITHM);

        config.setKeyObtentionIterations("1000");

        config.setPoolSize("1");

        config.setProviderName("SunJCE");

        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");

        config.setStringOutputType("base64");

        return config;

    }


}
