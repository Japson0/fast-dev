/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.custom.minio;

import net.evecom.custom.minio.driver.MinioDriver;
import net.evecom.custom.minio.driver.ServerInfo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

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
    public MinioDriver minioDriver(ServerInfo serverInfo, MinioProperties properties) {
        return new MinioDriver(serverInfo, properties);
    }

    /**
     * @param minioDriver
     * @return
     */
    @Bean
    @ConditionalOnExpression("#{environment['evecom.minio.endpoint']!=null}")
    public FileManager fileManager(MinioDriver minioDriver) {
        return new FileManager(minioDriver);
    }

    /**
     * @param minioDriver
     * @return
     */
    @Bean
    @ConditionalOnExpression("#{environment['evecom.minio.endpoint']!=null}")
    public BucketManager bucketManager(MinioDriver minioDriver) {
        return new BucketManager(minioDriver);
    }
}



