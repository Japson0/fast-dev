package com.nlecloud.spring.scaffold;

import com.nlecloud.spring.scaffold.api.user.IUPMSUserApi;
import com.nlecloud.spring.scaffold.filter.GlobalHeaderInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * <P><B>申明式客户端配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年06月27日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Configuration
public class WebClientConfig {

//    @LoadBalanced  // 关键注解，启用负载均衡
    @Bean
    RestClient.Builder loadBalancedRestClientBuilder() {
        return RestClient.builder()
                .requestInterceptor(new GlobalHeaderInterceptor());
    }

    @Bean
    public IUPMSUserApi upmsUserApi(RestClient.Builder restClientBuilder,
                                    @Value("${upms-user-center.url:http://nlecloud-upms-user-center-server.upms:19192}") String baseUrl) {
        RestClient restClient = restClientBuilder
                .baseUrl(baseUrl)  // 这里写服务名
                .build();

        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(RestClientAdapter.create(restClient))
                .build()
                .createClient(IUPMSUserApi.class);
    }

    @Bean
//    @ConditionalOnBean(FeignClientFactoryBean.class)
    public GlobalHeaderInterceptor globalHeaderInterceptor(){
        return new GlobalHeaderInterceptor();
    }
}
