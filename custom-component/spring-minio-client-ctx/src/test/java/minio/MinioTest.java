package minio;

import net.github.custom.minio.response.PresignedUpload;
import org.junit.Test;

public class MinioTest {


    @Test
    public void upload() {
        try {
            MinioUploadHandler handler = new MinioUploadHandler(
                "http://192.168.133.170:9000/",
                "hb-test",
                "newlandedu2022");

            String originalFilename = "test.pdf";

            // 生成有效期为2小时的上传URL
            PresignedUpload test = handler.generateSignedUploadUrl(
                    "test", originalFilename, 2);

            System.out.println("上传URL: " + test.getUploadUrl());
            System.out.println("存储文件名: " + test.getHeaders().toString());
//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}