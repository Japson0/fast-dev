package com.nlecloud.common.adapter.config;

/**
 * mybatis-plus 配置属性
 *
 * @author warrior
 */
public class MybatisPlusAutoFillProperties {
    /**
     * 是否开启自动填充字段
     */
    private Boolean enabled = true;
    /**
     * 是否开启了插入填充
     */
    private Boolean enableInsertFill = true;
    /**
     * 是否开启了更新填充
     */
    private Boolean enableUpdateFill = true;
    /**
     * 创建时间字段名
     */
    private String createTimeField = "createTime";
    /**
     * 更新时间字段名
     */
    private String updateTimeField = "updateTime";

    /**
     * 创建用户字段名
     */
    private String createPersonField = "createPerson";

    /**
     * 更新用户字段名
     */
    private String updatePersonField = "updatePerson";

    public Boolean getEnabled() {
        return enabled;
    }

    public Boolean getEnableInsertFill() {
        return enableInsertFill;
    }

    public Boolean getEnableUpdateFill() {
        return enableUpdateFill;
    }

    public String getCreateTimeField() {
        return createTimeField;
    }

    public String getUpdateTimeField() {
        return updateTimeField;
    }

    public String getCreatePersonField() {
        return createPersonField;
    }

    public String getUpdatePersonField() {
        return updatePersonField;
    }
}
