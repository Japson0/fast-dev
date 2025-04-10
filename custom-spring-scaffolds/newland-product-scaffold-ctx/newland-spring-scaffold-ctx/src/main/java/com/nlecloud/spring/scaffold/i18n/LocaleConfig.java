package com.nlecloud.spring.scaffold.i18n;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * <P><B>国际化配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月10日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Configuration
//@ConditionalOnProperty
public class LocaleConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.messages")
    public MessageSourceProperties messageSourceProperties() {
        return new MessageSourceProperties();
    }

    @Bean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
    public MessageSourceFactory messageSourceFactory(MessageSourceProperties messageSourceProperties){
        return new MessageSourceFactory(messageSourceProperties);
    }
}
