package com.nlecloud.spring.scaffold.common;

import cn.hutool.extra.spring.SpringUtil;
import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.annotation.enums.Sex;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserWrapper extends UserInfo {

    private Long userId;

    private String username;


    private Long tenantId;

    private Long schoolId;

    private Collection<String> roles;

    private  UserProxy userProxy;

    private UserInfo userInfo;

    private String token;


//    public UserWrapper(HttpHeaders headers){
//        String userId = headers.getFirst("x-userid-header");
//        String username = headers.getFirst("x-user-header");
//        String roles = headers.getFirst("x-role-header");
//        String tenantId = headers.getFirst("x-school-header");
//
//        if(userId!=null) {
//            this.userId=Long.valueOf(userId);
//            this.username=username;
//            //TODO 租户这里有可能没有
//            this.tenantId=tenantId.split(",");
//            if (roles != null) {
//                this.roles = Collections.EMPTY_SET;
//            } else {
//                String[] rolesSplit = roles.split(",");
//                this.roles = new HashSet<>(rolesSplit.length);
//                for (String role : rolesSplit) {
//                    this.roles.add(role);
//                }
//            }
//        }
//    }

    public UserWrapper(Long userId, String username,Long tenantId) {
        this(userId,username,tenantId,tenantId, Collections.EMPTY_SET);
    }

    public UserWrapper(Long userId,String username,Long schoolId,Collection<String> roles) {
        this(userId,username, schoolId,schoolId,roles);
    }

    public UserWrapper(Long userId,String username,Long tenantId,Long schoolId,Collection<String> roles) {
        this.userId = userId;
        this.username = username;
        this.tenantId=tenantId;
        this.schoolId=schoolId;
        this.roles=roles;
    }


    public UserWrapper(Long userId,String username,Long tenantId,Long schoolId,Collection<String> roles,String token) {
        this.userId = userId;
        this.username = username;
        this.tenantId=tenantId;
        this.schoolId=schoolId;
        this.roles=roles;
        this.token=token;
    }



    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public Long getTenantId() {
        return tenantId;
    }


    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Long getSchoolId() {
        return this.schoolId;
    }

    @Override
    public void setSchoolId(Long schoolId) {
        this.schoolId=schoolId;
    }

    @Override
    public Collection<String> getRoles() {
        return this.roles;
    }

    @Override
    public void setRoles(Collection<String> roles) {
        this.roles=roles;
    }

    @Override
    public Long getClassId() {
        return getUserInfo().getClassId();
    }

    @Override
    public void setClassId(Long classId) {
        getUserInfo().setClassId(classId);
    }

    @Override
    public String getEmail() {
        return getUserInfo().getEmail();
    }

    @Override
    public void setEmail(String email) {
        getUserInfo().setEmail(email);
    }

    @Override
    public String getSchoolName() {
        return getUserInfo().getSchoolName();
    }

    @Override
    public void setSchoolName(String schoolName) {
        getUserInfo().setSchoolName(schoolName);
    }

    @Override
    public String getClassName() {
        return getUserInfo().getClassName();
    }

    @Override
    public void setClassName(String className) {
        getUserInfo().setClassName(className);
    }

    @Override
    public String getStudentNo() {
        return getUserInfo().getStudentNo();
    }

    @Override
    public void setStudentNo(String studentNo) {
        getUserInfo().setStudentNo(studentNo);
    }

    @Override
    public String getProfessionName() {
        return getUserInfo().getProfessionName();
    }

    @Override
    public void setProfessionName(String professionName) {
        getUserInfo().setProfessionName(professionName);
    }



    @Override
    public String getAvatar() {
        return getUserInfo().getAvatar();
    }

    @Override
    public void setAvatar(String avatar) {
        getUserInfo().setAvatar(avatar);
    }

    @Override
    public Sex getSex() {
        return getUserInfo().getSex();
    }

    @Override
    public void setSex(Sex sex) {
        getUserInfo().setSex(sex);
    }

    @Override
    public void setTenantId(Long tenantId) {
        getUserInfo().setTenantId(tenantId);
    }

    @Override
    public String getPhone() {
        return getUserInfo().getPhone();
    }

    @Override
    public void setPhone(String phone) {
        getUserInfo().setPhone(phone);
    }


    @Override
    public String getNickName() {
        return userInfo.getNickName();
    }

    @Override
    public void setNickName(String nickName) {
        userInfo.setNickName(nickName);
    }

    @Override
    public List<Long> getManagerOrges() {
        return getUserInfo().getManagerOrges();
    }

    @Override
    public Long getOrgId() {
        return getUserInfo().getOrgId();
    }

    private UserInfo getUserInfo() {
        if(userInfo == null){
              this.userInfo = token!=null?checkUserProxy().getUserInfo(this.userId,token.substring("Bearer ".length())):checkUserProxy().getUserInfo(this.userId); //这里通过远程调用获取用户信息
        }
        return userInfo;
    }


    private UserProxy checkUserProxy(){
        if (userProxy == null) {
            synchronized (this) {
                if (userProxy == null) {
                    userProxy = SpringUtil.getBean(UserProxy.class);
                }
            }
        }
        return userProxy;
    }
}
