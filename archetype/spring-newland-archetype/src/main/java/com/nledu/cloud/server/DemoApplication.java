package com.nledu.cloud.server;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月02日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
public class DemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }
}
