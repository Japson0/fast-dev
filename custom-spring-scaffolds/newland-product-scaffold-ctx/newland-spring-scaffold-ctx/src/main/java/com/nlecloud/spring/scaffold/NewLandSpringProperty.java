package com.nlecloud.spring.scaffold;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@ConfigurationProperties(prefix = "nlecloud.product")
@Getter
@Setter
public class NewLandSpringProperty {

    private boolean tenantEnabled=false;

    /**
     * 租户忽略的表信息
     */
    private String[] ignoreTenantTable;
}
