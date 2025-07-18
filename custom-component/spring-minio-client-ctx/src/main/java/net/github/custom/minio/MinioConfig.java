
package net.github.custom.minio;

import io.minio.MinioClient;
import net.github.custom.minio.driver.MinioDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * MinioConfig
 *
 * @author Nick Lv
 * @created 2022/10/17 17:13
 */
@Configuration
@EnableConfigurationProperties({MinioProperties.class})
public class MinioConfig {


    @Bean
    @Scope("prototype")
    public MinioDriver minioDriver( MinioProperties properties) {
        return new MinioDriver(properties);
    }

    /**
     * @param minioClient
     * @return
     */
    @Bean
    @ConditionalOnExpression("#{environment['minio.endpoint']!=null}")
    @Lazy
    public FileManager fileManager(MinioClient minioClient) {
        return new FileManager(minioClient);
    }

}



