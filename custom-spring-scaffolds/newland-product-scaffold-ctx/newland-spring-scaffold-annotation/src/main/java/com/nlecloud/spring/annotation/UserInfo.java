package com.nlecloud.spring.annotation;

import com.nlecloud.spring.annotation.enums.Sex;

import java.io.Serializable;
import java.util.Collection;

/**
 * <P><B>用户信息:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserInfo implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 账号信息
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickName;


    /**
     * 学校ID
     */
    private Long schoolId;

    /**
     * tenantId
     */
    private Long tenantId;
    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 角色编码列表
     */
    private Collection<String> roles;

    /**
     * 班级ID
     */
    private Long classId;

    /**
     * 帮班级名称
     */
    private String className;

    /**
     * 学号
     */
    private String studentNo;

    /**
     * 专业名称
     */
    private String professionName;
    /**
     * 又想
     */
    private String email;

    /**
     * 头像url
     */
    private String avatar;

    /**
     * 性别
     */
    private Sex sex;

    private String phone;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTenantId() {
        return tenantId==null?schoolId:tenantId;
    }


    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
