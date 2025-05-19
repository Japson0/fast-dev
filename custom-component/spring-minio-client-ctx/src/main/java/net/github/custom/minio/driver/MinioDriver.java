
package net.github.custom.minio.driver;

import io.minio.MinioClient;
import net.github.custom.minio.MinioProperties;
import org.springframework.beans.factory.FactoryBean;

/**
 * minio的驱动类
 *
 * @author Nick Lv
 * @created 2022/10/12 17:14
 */
public class MinioDriver implements FactoryBean<MinioClient> {

    private MinioProperties minioProperties;



    public MinioDriver( MinioProperties properties) {
        this.minioProperties=properties;
    }



    @Override
    public MinioClient getObject() throws Exception {
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .region(minioProperties.getRegion())
                .build();
    }

    @Override
    public Class<MinioClient> getObjectType() {
        return MinioClient.class;
    }
}



