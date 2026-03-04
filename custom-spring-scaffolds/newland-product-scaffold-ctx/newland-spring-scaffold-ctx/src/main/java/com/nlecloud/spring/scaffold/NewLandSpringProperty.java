package com.nlecloud.spring.scaffold;

import com.nlecloud.spring.annotation.UserInfo;
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

    /**
     * 接口权限开关
     */
    private boolean apiPermissionEnabled=false;

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

    public boolean isApiPermissionEnabled() {
        return apiPermissionEnabled;
    }

    public void setApiPermissionEnabled(boolean apiPermissionEnabled) {
        this.apiPermissionEnabled = apiPermissionEnabled;
    }

    public static class DebugProperty {

        /**
         * debug总开关
         */
        private boolean enable=false;

        /**
         * 日志debug，会输出所有参数
         */
        @Deprecated
        private boolean logger = false;

        /**
         * 注入用户
         */
        private boolean injectUser = false;

        private DebugUser userInfo;

        public boolean isLogger() {
            return logger;
        }

        public void setLogger(boolean logger) {
            this.logger = logger;
        }

        public boolean isInjectUser() {
            return injectUser;
        }

        public void setInjectUser(boolean injectUser) {
            this.injectUser = injectUser;
        }

        public DebugUser getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(DebugUser userInfo) {
            this.userInfo = userInfo;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public static class DebugUser extends UserInfo {}
    }

}
