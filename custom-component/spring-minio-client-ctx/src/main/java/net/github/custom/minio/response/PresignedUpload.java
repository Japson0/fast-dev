package net.github.custom.minio.response;

import java.util.Map;

public class PresignedUpload {
    /**
     * 上传地址
     */
    private final String uploadUrl;

    /**
     * 转换后的路径名称
     */
    private final String shortName;
    /**
     * 文件名称
     */
    private final Map<String,String> headers;


    public PresignedUpload(String uploadUrl, String shortName, Map<String, String> headers) {
        this.uploadUrl = uploadUrl;
        this.shortName = shortName;
        this.headers = headers;
    }

    public String getShortName() {
        return shortName;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }


    public Map<String, String> getHeaders() {
        return headers;
    }
}