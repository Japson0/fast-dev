package com.nlecloud.spring.scaffold.i18n;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月10日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class MessageSourceFactory implements FactoryBean<MessageSource> {

    private final MessageSourceProperties properties;

    private final String defaultBasename="i18n/messages";

    public MessageSourceFactory(MessageSourceProperties properties) {
        this.properties = properties;
    }

    @Override
    public MessageSource getObject() throws Exception {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        List<String> base=new ArrayList<>();

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:i18n");

        for (Resource resource : resources) {
            base.add(resource.getURL()+"/messages");
        }

        messageSource.setBasenames(base.toArray(new String[0]));

        if (properties.getEncoding() != null) {
            messageSource.setDefaultEncoding(properties.getEncoding().name());
        }
        messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
        Duration cacheDuration = properties.getCacheDuration();
        if (cacheDuration != null) {
            messageSource.setCacheMillis(cacheDuration.toMillis());
        }
        messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
        messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
        return messageSource;
    }

    @Override
    public Class<?> getObjectType() {
        return MessageSource.class;
    }
}
