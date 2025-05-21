package minio;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Tags;
import net.github.custom.minio.response.PresignedUpload;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MinioUploadHandler {
    private final MinioClient minioClient;


    public MinioUploadHandler(String endpoint, String accessKey, String secretKey) {
        this.minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
    
    /**
     * 生成预签名上传URL并配置文件名转换
     * @param bucketName 存储桶名称
     * @param originalFilename 原始文件名
     * @param expiryHours URL有效期(小时)
     * @return 包含上传URL和存储文件名的对象
     */
    public PresignedUpload generateSignedUploadUrl(
            String bucketName, 
            String originalFilename,
            int expiryHours) throws Exception {
        
        // 1. 生成存储用的短文件名（时间戳+随机串）
        String fileExtension = originalFilename.substring(
            originalFilename.lastIndexOf('.'));
        String storedFilename = System.currentTimeMillis() + "_" 
            + UUID.randomUUID().toString().substring(0, 6) + fileExtension;
        

        // 3. 设置上传参数（包括标签）
        Map<String, String> extraHeaders = new HashMap<>();
        extraHeaders.put("x-amz-tagging", "original-filename=" + originalFilename);
        
        // 4. 生成预签名PUT URL
        String uploadUrl = minioClient.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.PUT)
                .bucket(bucketName)
                .object(storedFilename)
                .expiry(expiryHours, TimeUnit.HOURS)
                .extraHeaders(extraHeaders)
                .build());
        
        return new PresignedUpload(uploadUrl, storedFilename,extraHeaders);
    }
    
    /**
     * 获取对象的原始文件名（通过标签）
     */
    public String getOriginalFilename(String bucketName, String storedName) 
            throws Exception {
        Tags tags = minioClient.getObjectTags(
            GetObjectTagsArgs.builder()
                .bucket(bucketName)
                .object(storedName)
                .build());
        return tags.get().get("original-filename");
    }
    
    // 辅助类

}