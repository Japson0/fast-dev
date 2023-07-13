/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.custom.minio.driver;

/**
 * ServerInfo
 *
 * @author Nick Lv
 * @created 2022/10/12 17:07
 */
public interface ServerInfo {


    /**
     * 获取用户名
     *
     * @return
     */
    String getUsername();

    /**
     * 获取密码
     *
     * @return
     */
    String getPassword();

    /**
     * 获取桶名
     *
     * @return
     */
    String getBucket();

}



