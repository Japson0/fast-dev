/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp;

import java.io.Serializable;

/**
 * 平台管理-角色信息表 实体
 *
 * @author Jay Jiang
 */
public class RoleInfo implements Serializable {
    /**
     * Id
     */
    private Long id;
    /**
     * Name
     */
    private String name;
    /**
     * En name
     */
    private String enName;
    /**
     * Is default
     */
    private Integer isDefault;
    /**
     * Tenant id
     */
    private Long tenantId;

    /**
     * Gets tenant id *
     *
     * @return the tenant id
     */
    public Long getTenantId() {
        return tenantId;
    }

    /**
     * Sets tenant id *
     *
     * @param tenantId tenant id
     */
    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Gets id *
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id *
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets name *
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name *
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets en name *
     *
     * @return the en name
     */
    public String getEnName() {
        return enName;
    }

    /**
     * Sets en name *
     *
     * @param enName en name
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /**
     * Gets is default *
     *
     * @return the is default
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     * Sets is default *
     *
     * @param isDefault is default
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

}
