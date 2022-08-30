/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.fastdev.ddp.model;

/**
 * 携带租户标识的父类
 *
 * @author Nick Lv
 * @created 2022/8/26 10:27
 */
public abstract class TenantEntity extends DataEntity {
    /**
     * 租户字段
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
}



