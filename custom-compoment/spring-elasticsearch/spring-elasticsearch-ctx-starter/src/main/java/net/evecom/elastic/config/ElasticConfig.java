package net.evecom.elastic.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import net.evecom.elastic.RestClientFactory;
import net.evecom.elastic.service.ElasticSearch;
import net.evecom.elastic.service.impl.ElasticSearchImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <P><B>es客户端构建工具:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月14日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@EnableConfigurationProperties(ElasticSearchProperties.class)
@Configuration
public class ElasticConfig {

    /**
     * ES配置
     */
    private final ElasticSearchProperties elasticSearchProperties;

    public ElasticConfig(ElasticSearchProperties elasticSearchProperties) {
        this.elasticSearchProperties = elasticSearchProperties;
    }

    @Bean
    public RestClientFactory elasticsearchClient(){
        return new RestClientFactory(elasticSearchProperties);
    }

    @Bean
    public ElasticSearch elasticSearch(ElasticsearchClient elasticsearchClient){
        return new ElasticSearchImpl(elasticsearchClient);
    }
}
