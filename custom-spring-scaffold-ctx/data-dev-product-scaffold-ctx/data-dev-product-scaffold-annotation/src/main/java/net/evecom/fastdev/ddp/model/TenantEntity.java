/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.fastdev.ddp.model;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 携带租户标识的父类
 *
 * @author Nick Lv
 * @created 2022/8/26 10:27
 */
public abstract class TenantEntity extends DataEntity {
    /**
     * 序列号
     */
    private static final long serialVersionUID = -4393596839845121169L;
    /**
     * 租户字段
     */
    @JsonIgnore
    @TableField(
            updateStrategy = FieldStrategy.NEVER, insertStrategy = FieldStrategy.NEVER)
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

    @Override
    public void clear() {
        super.clear();
        this.tenantId = null;
    }
}



