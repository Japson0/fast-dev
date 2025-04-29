package com.nlecloud.spring.scaffold;

import com.nlecloud.spring.scaffold.debug.DebugProperty;
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
public class NewLandSpringProperty {

    private boolean tenantEnabled=false;


    /**
     * 租户忽略的表信息
     */
    private String[] ignoreTenantTable;

    private DebugProperty debug;

    public boolean isTenantEnabled() {
        return tenantEnabled;
    }

    public void setTenantEnabled(boolean tenantEnabled) {
        this.tenantEnabled = tenantEnabled;
    }

    public String[] getIgnoreTenantTable() {
        return ignoreTenantTable;
    }

    public void setIgnoreTenantTable(String[] ignoreTenantTable) {
        this.ignoreTenantTable = ignoreTenantTable;
    }

    public DebugProperty getDebug() {
        return debug;
    }

    public void setDebug(DebugProperty debug) {
        this.debug = debug;
    }
}
