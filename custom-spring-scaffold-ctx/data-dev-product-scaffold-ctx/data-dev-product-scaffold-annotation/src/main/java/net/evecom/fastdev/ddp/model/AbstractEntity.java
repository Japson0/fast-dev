package net.evecom.fastdev.ddp.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

import static net.evecom.fastdev.ddp.enums.TableColumnName.*;

/**
 * <P><B>抽象实体:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年11月17日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public abstract class AbstractEntity<T extends Serializable>
        implements net.evecom.fastdev.mybatis.annotation.BaseEntity<T> {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -2746833010184326831L;
    /**
     * 创建时间
     */
    @TableField(value = TIME_CREATED, insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NEVER, fill = FieldFill.INSERT)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date timeCreated;

    /**
     * 修改时间
     */
    @TableField(value = TIME_MODIFIED, insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT_UPDATE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date timeModified;
    /**
     * 创建人
     */
    @JsonIgnore
    @TableField(value = CREATOR_ID, insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NEVER, fill = FieldFill.INSERT)
    private Long creatorId;
    /**
     * 修改人
     */
    @JsonIgnore
    @TableField(value = MODIFIER_ID, insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT_UPDATE)
    private Long modifierId;

    /**
     * 创建机构
     */
    @JsonIgnore
    @TableField(value = CREATE_ORG_ID)
    private Long createOrgId;

    /**
     * 修改组织
     */
    @JsonIgnore
    @TableField(value = MODIFY_ORG_ID)
    private Long modifyOrgId;

    /**
     *
     */
    @TableField(value = CREATOR_NAME, insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NEVER, fill = FieldFill.INSERT)
    @JsonIgnore
    private String creatorName;

    /**
     * 修改人名称
     */
    @TableField(value = MODIFIER_NAME, insertStrategy = FieldStrategy.NOT_NULL, updateStrategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
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

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Date getTimeModified() {
        return timeModified;
    }

    public void setTimeModified(Date timeModified) {
        this.timeModified = timeModified;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public Long getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(Long createOrgId) {
        this.createOrgId = createOrgId;
    }

    public Long getModifyOrgId() {
        return modifyOrgId;
    }

    public void setModifyOrgId(Long modifyOrgId) {
        this.modifyOrgId = modifyOrgId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public String getCreateOrgName() {
        return createOrgName;
    }

    public void setCreateOrgName(String createOrgName) {
        this.createOrgName = createOrgName;
    }

    public String getModifyOrgName() {
        return modifyOrgName;
    }

    public void setModifyOrgName(String modifyOrgName) {
        this.modifyOrgName = modifyOrgName;
    }
}
