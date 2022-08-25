/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp;

import java.util.List;

/**
 * 用户信息
 *
 * @author Nick Lv
 */
public class UserResource {
    /**
     * User id
     */
    private Long userId;
    /**
     * Tenant id
     */
    private Long tenantId;
    /**
     * Login name
     */
    private String loginName;
    /**
     * Name
     */
    private String name;
    /**
     * Img
     */
    private String img;
    /**
     * Roles
     */
    private List<RoleInfo> roles;
    /**
     * Depts
     */
    private List<DeptInfo> depts;
    /**
     * Tenant
     */
    private TenantInfo tenant;
    /**
     * Pwd updated
     */
    private Integer pwdUpdated;
    /**
     * Jwt
     */
    private String jwt;

    /**
     * Gets user id *
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id *
     *
     * @param userId user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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

    /**
     * Gets login name *
     *
     * @return the login name
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Sets login name *
     *
     * @param loginName login name
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
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
     * Gets img *
     *
     * @return the img
     */
    public String getImg() {
        return img;
    }

    /**
     * Sets img *
     *
     * @param img img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * Gets roles *
     *
     * @return the roles
     */
    public List<RoleInfo> getRoles() {
        return roles;
    }

    /**
     * Sets roles *
     *
     * @param roles roles
     */
    public void setRoles(List<RoleInfo> roles) {
        this.roles = roles;
    }

    /**
     * Gets depts *
     *
     * @return the depts
     */
    public List<DeptInfo> getDepts() {
        return depts;
    }

    /**
     * Sets depts *
     *
     * @param depts depts
     */
    public void setDepts(List<DeptInfo> depts) {
        this.depts = depts;
    }

    /**
     * Gets tenant *
     *
     * @return the tenant
     */
    public TenantInfo getTenant() {
        return tenant;
    }

    /**
     * Sets tenant *
     *
     * @param tenant tenant
     */
    public void setTenant(TenantInfo tenant) {
        this.tenant = tenant;
    }

    /**
     * Gets pwd updated *
     *
     * @return the pwd updated
     */
    public Integer getPwdUpdated() {
        return pwdUpdated;
    }

    /**
     * Sets pwd updated *
     *
     * @param pwdUpdated pwd updated
     */
    public void setPwdUpdated(Integer pwdUpdated) {
        this.pwdUpdated = pwdUpdated;
    }

    /**
     * Gets jwt *
     *
     * @return the jwt
     */
    public String getJwt() {
        return jwt;
    }

    /**
     * Sets jwt *
     *
     * @param jwt jwt
     */
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}



