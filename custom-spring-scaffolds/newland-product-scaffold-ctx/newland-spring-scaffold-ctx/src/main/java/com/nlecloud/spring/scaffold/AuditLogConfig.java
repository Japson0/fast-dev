package com.nlecloud.spring.scaffold;

import com.nlecloud.spring.scaffold.aop.AuditLogAspect;
import com.nlecloud.spring.scaffold.property.AuditLogProperty;
import org.apache.rocketmq.client.core.RocketMQClientTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 审计日志配置.
 *
 * @author Japson Huang
 */
@Configuration
@ConditionalOnClass(RocketMQClientTemplate.class)
@ConditionalOnProperty(prefix = "nlecloud.product.audit-log", name = "enable", havingValue = "true")
@EnableConfigurationProperties(AuditLogProperty.class)
@AutoConfigureAfter(name = "org.apache.rocketmq.client.autoconfigure.RocketMQAutoConfiguration")
public class AuditLogConfig {

    @Bean
    @ConditionalOnBean(RocketMQClientTemplate.class)
    @ConditionalOnMissingBean
    public AuditLogAspect auditLogAspect(RocketMQClientTemplate rocketMQClientTemplate,
                                         AuditLogProperty auditLogProperty) {
        return new AuditLogAspect(rocketMQClientTemplate, auditLogProperty);
    }
}
