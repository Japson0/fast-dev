package com.nlecloud.spring.scaffold.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.github.fastdev.common.annotation.Update;
import net.github.fastdev.mybatis.annotation.BaseObjEntity;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * <P><B>基础类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class BaseEntity implements BaseObjEntity<Long> {

    /**
     * 逐渐
     */
    @TableId(type = IdType.ASSIGN_ID)
    @NotNull(groups = Update.class)
    private Long id;

    @TableField(value = TableColumn.CREATE_USER_ID,fill = FieldFill.INSERT)
    @JsonIgnore
    private Long createPerson;

    @TableField(value = TableColumn.UPDATE_USER_ID,fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    private Long updatePerson;

    @TableField(value = TableColumn.CREATE_TIME,fill = FieldFill.INSERT)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createTime;


    @TableField(value = TableColumn.UPDATE_TIME,fill = FieldFill.INSERT_UPDATE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp updateTime;

    @Override
    public  Long getId() {
        return id;
    }

    @Override
    public void setId( Long id) {
        this.id = id;
    }


    public Long getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }

    public Long getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(Long updatePerson) {
        this.updatePerson = updatePerson;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
