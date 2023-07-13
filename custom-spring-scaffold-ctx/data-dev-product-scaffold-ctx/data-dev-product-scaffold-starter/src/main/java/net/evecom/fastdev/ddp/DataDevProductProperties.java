package net.evecom.fastdev.ddp;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月24日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@ConfigurationProperties(prefix = "evecom.product")
public class DataDevProductProperties {

    /**
     * debugger模式
     */
    private Debug debug;

    /**
     * 是否开启租户
     */
    private boolean enableTenant = true;

    /**
     * 是否开启模块注入。
     */
    private boolean enableModule = false;
    /**
     * 租户忽略的表信息
     */
    private String[] ignoreTenantTable;


    public Debug getDebug() {
        return debug;
    }

    public void setDebug(Debug debug) {
        this.debug = debug;
    }

    public boolean isEnableTenant() {
        return enableTenant;
    }

    public void setEnableTenant(boolean enableTenant) {
        this.enableTenant = enableTenant;
    }

    public String[] getIgnoreTenantTable() {
        return ignoreTenantTable;
    }

    public void setIgnoreTenantTable(String[] ignoreTenantTable) {
        this.ignoreTenantTable = ignoreTenantTable;
    }

    public boolean isEnableModule() {
        return enableModule;
    }

    public void setEnableModule(boolean enableModule) {
        this.enableModule = enableModule;
    }

    /**
     * debugger模式
     * RevisionTrail:(Date/Author/Description)
     * 2022年08月25日
     *
     * @author Japson Huang
     */
    public static class Debug {

        /**
         * 是否开启
         */
        private boolean enable;

        /**
         * 用户信息
         */
        private DebuggerUser user;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public DebuggerUser getUser() {
            return user;
        }

        public void setUser(DebuggerUser user) {
            this.user = user;
        }
    }

    public static class DebuggerUser extends UserInfo {

        public DebuggerUser() {
            super();
        }
    }
}
