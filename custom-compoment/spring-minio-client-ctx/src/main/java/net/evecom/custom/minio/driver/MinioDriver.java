/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.custom.minio.driver;

import io.minio.MinioClient;
import net.evecom.custom.minio.MinioProperties;

/**
 * minio的驱动类
 *
 * @author Nick Lv
 * @created 2022/10/12 17:14
 */
public class MinioDriver {
    /**
     * minio的客户端
     */
    private MinioClient client;
    /**
     * 服务信息
     */
    private ServerInfo serverInfo;
    /**
     * minio地址
     */
    private final String endPoint;
    /**
     * region的位置
     */
    private final String region;

    public String getEndPoint() {
        return endPoint;
    }

    public String getRegion() {
        return region;
    }

    public MinioClient getClient() {
        return client;
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public MinioDriver(ServerInfo serverInfo, MinioProperties properties) {
        this.serverInfo = serverInfo;
        this.endPoint = properties.getEndpoint();
        this.region = properties.getRegion();
        this.initClient();
    }


    /**
     * 初始化客户端
     */
    private void initClient() {
        this.client = MinioClient.builder()
                .endpoint(endPoint)
                .credentials(serverInfo.getUsername(), serverInfo.getPassword())
                .region(region)
                .build();
    }

}



