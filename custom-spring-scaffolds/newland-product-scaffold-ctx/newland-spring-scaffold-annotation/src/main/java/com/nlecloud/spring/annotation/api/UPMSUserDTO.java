package com.nlecloud.spring.annotation.api;


import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author llj
 * @since 2025/5/12 13:41
 */

public class UPMSUserDTO {
    private static final long serialVersionUID = 1;

    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 昵称
     */
    private String name;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别，0：女，1：男
     */
    private Integer sex;

    /**
     * 状态
     */
    private Integer status;


    /**
     * 最后登入IP
     */
    private String lastLoginIp;

    /**
     * 登录次数
     */
    private Long loginTimes;

    /**
     * 学校ID
     */
    private String collegeId;

    /**
     * 班级ID
     */
    private String classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 学校名
     */
    private String collegeName;

    /**
     * 最近登录时间
     */
    private String lastLoginTime;

    /**
     * 注册时间
     */
    private String registerTime;

    private String professionCode;

    private String professionName;

    private String studentNo;

    /**
     * 租户管理员的租户ID列表
     */
    private Set<Long> tenantAdminTenantIds;

    /**
     * 机构管理员的机构ID列表
     */
    private Map<Long,List<Long>> orgAdminOrgIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Long getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Long loginTimes) {
        this.loginTimes = loginTimes;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getProfessionCode() {
        return professionCode;
    }

    public void setProfessionCode(String professionCode) {
        this.professionCode = professionCode;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public Set<Long> getTenantAdminTenantIds() {
        return tenantAdminTenantIds;
    }

    public void setTenantAdminTenantIds(Set<Long> tenantAdminTenantIds) {
        this.tenantAdminTenantIds = tenantAdminTenantIds;
    }

    public Map<Long, List<Long>> getOrgAdminOrgIds() {
        return orgAdminOrgIds;
    }

    public void setOrgAdminOrgIds(Map<Long, List<Long>> orgAdminOrgIds) {
        this.orgAdminOrgIds = orgAdminOrgIds;
    }
}
