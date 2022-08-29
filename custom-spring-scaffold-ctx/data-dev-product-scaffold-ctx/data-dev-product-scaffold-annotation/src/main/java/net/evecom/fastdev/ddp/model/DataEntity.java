/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.fastdev.ddp.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

import static net.evecom.fastdev.ddp.enums.TableColumnName.*;
import static net.evecom.fastdev.ddp.enums.TableColumnName.MODIFY_ORG_NAME;

/**
 * DataEntity
 *
 * @author Nick Lv
 * @created 2022/8/26 10:25
 */
public abstract class DataEntity extends BaseEntity {
    /**
     * 创建时间
     */
    @TableField(value = TIME_CREATED, insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NEVER, fill = FieldFill.INSERT)
    private Date timeCreated;

    /**
     * 修改时间
     */
    @TableField(value = TIME_MODIFIED, insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT_UPDATE)
    private Date timeModified;
    /**
     * 创建人
     */
    @JsonIgnore
    @TableField(value = CREATOR_ID, insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NEVER, fill = FieldFill.INSERT)
    private String creatorId;
    /**
     * 修改人
     */
    @JsonIgnore
    @TableField(value = MODIFIER_ID, insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT_UPDATE)
    private String modifierId;

    /**
     * 创建机构
     */
    @JsonIgnore
    @TableField(value = CREATE_ORG_ID)
    private String createOrgId;

    /**
     * 修改组织
     */
    @JsonIgnore
    @TableField(value = MODIFY_ORG_ID)
    private String modifyOrgId;

    /**
     *
     */
    @TableField(value = CREATOR_NAME, insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NEVER, fill = FieldFill.INSERT)
    private String creatorName;

    /**
     * 修改人名称
     */
    @TableField(value = MODIFIER_NAME, insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT_UPDATE)
    private String modifierName;
    /**
     * Create org name
     */
    @JsonIgnore
    @TableField(value = CREATE_ORG_NAME)
    private String createOrgName;
    /**
     * Modify org name
     */
    @JsonIgnore
    @TableField(value = MODIFY_ORG_NAME)
    private String modifyOrgName;

    /**
     * Gets create org name *
     *
     * @return the create org name
     */
    public String getCreateOrgName() {
        return createOrgName;
    }

    /**
     * Sets create org name *
     *
     * @param createOrgName create org name
     */
    public void setCreateOrgName(String createOrgName) {
        this.createOrgName = createOrgName;
    }

    /**
     * Gets modify org name *
     *
     * @return the modify org name
     */
    public String getModifyOrgName() {
        return modifyOrgName;
    }

    /**
     * Sets modify org name *
     *
     * @param modifyOrgName modify org name
     */
    public void setModifyOrgName(String modifyOrgName) {
        this.modifyOrgName = modifyOrgName;
    }


    /**
     * 描述： Gets creator id.
     *
     * @return the creator id
     * @author Nick Lv
     * @created 2017 /07/06 17:41:31
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * Sets creator id.
     *
     * @param creatorId the creator id
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }


    /**
     * 描述： Gets creator org id.
     *
     * @return the creator org id
     * @author Nick Lv
     * @created 2017 /07/06 17:41:31
     */
    public String getCreateOrgId() {
        return createOrgId;
    }

    /**
     * Sets create org id.
     *
     * @param createOrgId the create org id
     */
    public void setCreateOrgId(String createOrgId) {
        this.createOrgId = createOrgId;
    }


    /**
     * Gets creator name.
     *
     * @return the creator name
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * Sets creator name.
     *
     * @param creatorName the creator name
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * Gets modifier name.
     *
     * @return the modifier name
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * Sets modifier name.
     *
     * @param modifierName the modifier name
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    /**
     * 描述： Gets modifier id.
     *
     * @return the modifier id
     * @author Nick Lv
     * @created 2017 /07/06 17:41:32
     */
    public String getModifierId() {
        return modifierId;
    }

    /**
     * Sets modifier id.
     *
     * @param modifierId the modifier id
     */
    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 描述： Gets time modified.
     *
     * @return the time modified
     * @author Nick Lv
     * @created 2017 /07/06 17:41:32
     */
    public Date getTimeModified() {
        return timeModified;
    }

    /**
     * Sets time modified.
     *
     * @param timeModified the time modified
     */
    public void setTimeModified(Date timeModified) {
        this.timeModified = timeModified;
    }

    /**
     * 描述： Gets time created.
     *
     * @return the time created
     * @author Nick Lv
     * @created 2017 /07/06 17:41:31
     */
    public Date getTimeCreated() {
        return timeCreated;
    }

    /**
     * Sets create time.
     *
     * @param timeCreated the create time
     */
    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    /**
     * 描述 Gets modifier org.
     *
     * @return the modifier org
     * @author Submarine Lin
     * @created 2017 /09/14 08:47:49
     */
    public String getModifyOrgId() {
        return modifyOrgId;
    }

    /**
     * Sets modify org id.
     *
     * @param modifyOrgId the modify org id
     */
    public void setModifyOrgId(String modifyOrgId) {
        this.modifyOrgId = modifyOrgId;
    }
}



