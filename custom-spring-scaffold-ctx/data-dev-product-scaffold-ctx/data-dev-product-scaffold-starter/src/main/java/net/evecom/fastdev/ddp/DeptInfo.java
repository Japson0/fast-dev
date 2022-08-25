/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp;

import java.io.Serializable;

/**
 * 平台管理-部门信息表 实体
 *
 * @author Jay Jiang
 */
public class DeptInfo implements Serializable {
    /**
     * id
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
     * Parent id
     */
    private Long parentId;
    /**
     * Full name
     */
    private String fullName;
    /**
     * Type
     */
    private Integer type;
    /**
     * Contact name
     */
    private String contactName;
    /**
     * Contact num
     */
    private String contactNum;
    /**
     * Contact addr
     */
    private String contactAddr;
    /**
     * Sort num
     */
    private Integer sortNum;
    /**
     * Tenant id
     */
    private Long tenantId;

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
     * Gets parent id *
     *
     * @return the parent id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Sets parent id *
     *
     * @param parentId parent id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * Gets full name *
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets full name *
     *
     * @param fullName full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets type *
     *
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * Sets type *
     *
     * @param type type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * Gets contact name *
     *
     * @return the contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets contact name *
     *
     * @param contactName contact name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Gets contact num *
     *
     * @return the contact num
     */
    public String getContactNum() {
        return contactNum;
    }

    /**
     * Sets contact num *
     *
     * @param contactNum contact num
     */
    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    /**
     * Gets contact addr *
     *
     * @return the contact addr
     */
    public String getContactAddr() {
        return contactAddr;
    }

    /**
     * Sets contact addr *
     *
     * @param contactAddr contact addr
     */
    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
    }

    /**
     * Gets sort num *
     *
     * @return the sort num
     */
    public Integer getSortNum() {
        return sortNum;
    }

    /**
     * Sets sort num *
     *
     * @param sortNum sort num
     */
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

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
