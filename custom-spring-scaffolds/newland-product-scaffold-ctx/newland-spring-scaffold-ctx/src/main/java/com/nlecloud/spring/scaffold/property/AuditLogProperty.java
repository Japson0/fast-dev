package com.nlecloud.spring.scaffold.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <P><B>审计日志配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年09月07日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "nlecloud.product.audit-log")
public class AuditLogProperty {

    private boolean enable = false;

    private String topic = "AUDIT_MESSAGE";
}
