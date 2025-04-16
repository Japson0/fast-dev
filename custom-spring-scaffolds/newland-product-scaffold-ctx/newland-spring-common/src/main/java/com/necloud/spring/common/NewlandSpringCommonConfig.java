package com.necloud.spring.common;

import com.necloud.spring.common.handle.UserProxy;
import com.necloud.spring.common.i18n.LocaleConfig;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月16日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Import(LocaleConfig.class)
@Configuration
@AutoConfigureBefore({MessageSourceAutoConfiguration.class})
public class NewlandSpringCommonConfig {


    @Bean
    public UserProxy userProxy(){
        return new UserProxy();
    }
}
