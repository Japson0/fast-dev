package net.evecom.fastdev.cloud;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * <P><B>Could配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年07月23日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties({RestTemplateProperties.class})
public class EvecomSpringCloudConfig implements WebMvcConfigurer {

    /**
     * resttemplate配置
     */
    private final RestTemplateProperties restTemplateProperties;

    public EvecomSpringCloudConfig(RestTemplateProperties restTemplateProperties) {
        this.restTemplateProperties = restTemplateProperties;
    }

    /**
     * 另Nacos权重生效
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月23日
     *
     * @author Japson Huang
     */
    @Bean
    @Scope(value = "prototype")
    public IRule loadBalanceRule() {
        return new NacosRule();
    }


    @Bean
    @LoadBalanced
    RestTemplate restTemplate(@Autowired(required = false) ClientHttpRequestFactory factory, ObjectMapper objectMapper) {
        if (factory == null) {
            SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            simpleClientHttpRequestFactory.setConnectTimeout(restTemplateProperties.getConnectTimeOut());
            ;
            simpleClientHttpRequestFactory.setReadTimeout(restTemplateProperties.getReadTimeOut());
            factory = simpleClientHttpRequestFactory;
        }
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(objectMapper);
        messageConverters.add(jsonMessageConverter);
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setMessageConverters(messageConverters);
        return new RestTemplate(factory);
    }

}
