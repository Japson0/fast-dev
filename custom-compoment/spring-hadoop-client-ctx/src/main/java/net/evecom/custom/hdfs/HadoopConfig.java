package net.evecom.custom.hdfs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <P><B>hadoop配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年10月12日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Configuration
@EnableConfigurationProperties(HdfsProperties.class)
public class HadoopConfig {

    /**
     * hdfs配置
     */
    private final HdfsProperties hdfsProperties;

    public HadoopConfig(HdfsProperties hdfsProperties) {
        this.hdfsProperties = hdfsProperties;
    }

    /**
     * hdfs客户端
     * RevisionTrail:(Date/Author/Description)
     * 2022年10月12日
     *
     * @author Japson Huang
     */
    @Bean
    @ConditionalOnExpression("#{environment['evecom.hadoop.hdfs']!=null}")
    public HdfsClientFactory hdfsClientFactory() {
        return new HdfsClientFactory(hdfsProperties);
    }
}
