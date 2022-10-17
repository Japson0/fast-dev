/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.custom.minio;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MinioProperties
 *
 * @author Nick Lv
 * @created 2022/10/17 17:11
 */
@ConfigurationProperties(prefix = "evecom.hadoop")
public class MinioProperties {
    /**
     * minio服务地址
     * 例如：http://ip:9000
     */
    private String endpoint;
    /**
     * 区域，需和minio的配置保持一致
     * 默认为:us-east-1
     */
    private String region = "us-east-1";

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}



