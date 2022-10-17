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
public class ServerInfo {
    /**
     * minio服务地址
     * 例如：http://ip:9000
     */
    private String endpoint;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 区域，需和minio的配置保持一致
     * 默认为:us-east-1
     */
    private String region = "us-east-1";
    /**
     * 桶名
     */
    private String bucket;


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String endpoint;
        private String username;
        private String password;
        private String region;
        private String bucket;

        private Builder() {
        }

        public static Builder aServerInfo() {
            return new Builder();
        }

        public Builder endpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder region(String region) {
            this.region = region;
            return this;
        }

        public Builder bucket(String bucket) {
            this.bucket = bucket;
            return this;
        }

        public ServerInfo build() {
            ServerInfo serverInfo = new ServerInfo();
            serverInfo.setEndpoint(endpoint);
            serverInfo.setUsername(username);
            serverInfo.setPassword(password);
            serverInfo.setRegion(region);
            serverInfo.setBucket(bucket);
            return serverInfo;
        }
    }
}



