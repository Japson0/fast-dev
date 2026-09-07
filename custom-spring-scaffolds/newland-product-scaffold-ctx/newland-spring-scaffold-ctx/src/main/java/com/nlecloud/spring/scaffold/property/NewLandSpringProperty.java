package com.nlecloud.spring.scaffold.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collection;

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



    /**
     * 接口权限开关
     */
    private boolean apiPermissionEnabled=false;

    private DebugProperty debug;


    private TableConfigProperty tableConfig;


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

        private String createPersonColumnName="create_person";


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

        private DebugUserInfo userInfo;


    }


    @Getter
    @Setter
    public static class DebugUserInfo {
        /**
         * 用户ID
         */
        private Long userId;
        /**
         * 账号信息
         */
        private String username;

        /**
         * 学校ID
         */
        private Long schoolId;

        /**
         * tenantId
         */
        private Long tenantId;

        /**
         * 角色编码列表
         */
        private Collection<String> roles;
    }
}
