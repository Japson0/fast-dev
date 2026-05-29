package com.nlecloud.spring.scaffold;

import com.nlecloud.spring.annotation.UserInfo;
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
public class NewLandSpringProperty {



    /**
     * 接口权限开关
     */
    private boolean apiPermissionEnabled=false;

    private DebugProperty debug;


    private TableConfigProperty tableConfig;

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

    public TableConfigProperty getTableConfig() {
        return tableConfig;
    }

    public void setTableConfig(TableConfigProperty tableConfig) {
        this.tableConfig = tableConfig;
    }

    @Getter
    @Setter
    public static class TableConfigProperty{

        /**
         * 接口权限开关
         */
        private boolean tenantEnabled=false;
        /**
         * 租户忽略的表信息
         */
        private String[] ignoreTenantTable;

        /**
         * 租户字段名称
         */
        private String tenantColumnName="tenant_id";

        /**
         * 接口权限开关
         */
        private boolean dataPermissionEnabled=false;

        /**
         * 数据权限忽略表
         */
        private String[] ignoreDataPermissionTable;

        /**
         * 机构字段名称
         */
        private String orgColumnName="org_id";


    }

    @Getter
    @Setter
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

        private UserInfo userInfo;

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

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

    }

}
