
package com.nlecloud.spring.scaffold.common;


import com.nlecloud.spring.annotation.OrgInfo;
import com.nlecloud.spring.annotation.TenantInfo;
import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.annotation.enums.TenantType;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Optional;

/**
 * <P><B>用户工具类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年10月29日 CREATE
 *
 * @author Japson HuangLoggerTracesService
 * AutoMetaObjectHandle
 * @version 1.0
 */
public class UserContext {

    /**
     * 用户信息
     */
    private final static ThreadLocal<UserInfo> USER_INFO_LOCAL=new ThreadLocal<>();

    private static final UserWrapper robotUser=new UserWrapper(0L,"system",0L);

    public static UserWrapper getRobotUser() {
        return  robotUser;
    }

    private UserContext() {
    }

    public static boolean isLogin(){
        return hasUser();
    }

    public static void clean() {
        USER_INFO_LOCAL.remove();
    }

    public static Long getUserId() {
        return getUserInfo().getUserId();
    }

    public static String getUserName() {
        return getUserInfo().getUsername();
    }

    public static Long getTenantId() {
        return getUserInfo().getTenantId();
    }


    public static Collection<String> getRoles(){
        return getUserInfo().getRoles();
    }


    public static UserInfo getUserInfo() {
        return USER_INFO_LOCAL.get();
    }

    public static Long getOrgId(){
        OrgInfo orgInfo = getUserInfo().getOrgInfo();
        return orgInfo==null?null:orgInfo.getId();
    }


    public static boolean isAdmin(){
        return getRoles().contains("admin");
    }

    /**
     *是否是租户管理员
     *RevisionTrail:(Date/Author/Description)
     * 2026年05月27日
     *@author Japson Huang
     *
    */
    public static boolean isTenantAdmin(){
        TenantInfo tenantInfo = getUserInfo().getTenantInfo();
        return tenantInfo==null?false:tenantInfo.isAdmin();
    }

    /**
     *是否个人租户
     *RevisionTrail:(Date/Author/Description)
     * 2026年08月14日
     *@author Japson Huang
     *
    */
    public static boolean isPersonTenant(){
        TenantInfo tenantInfo = getUserInfo().getTenantInfo();
        return tenantInfo==null?false:tenantInfo.getType()== TenantType.PERSON;
    }

    /**
     *是否机构管理员
     *RevisionTrail:(Date/Author/Description)
     * 2026年05月29日
     *@author Japson Huang
     *
    */
    public static boolean isOrgAdmin(){
        OrgInfo orgInfo = getUserInfo().getOrgInfo();
        return orgInfo==null?false:orgInfo.isAdmin();
    }



    /**
     *手机号验证
     *RevisionTrail:(Date/Author/Description)
     * 2026年07月07日
     *@author Japson Huang
     *
    */
    public static boolean isPhoneVerify(){

        return getUserInfo().isPhoneVerify();
    }

    /**
     *后续废弃schoolId,请取tenantId
     *RevisionTrail:(Date/Author/Description)
     * 2026年07月29日
     *@author Japson Huang
     *
    */
    @Deprecated
    public static Long getSchoolId(){
        return getUserInfo().getSchoolId();
    }


    public static void setUserInfo(UserInfo userInfo){
        USER_INFO_LOCAL.set(userInfo);
    }


    public static boolean hasUser(){
        return USER_INFO_LOCAL.get()!=null;
    }

    public static boolean isRobot(){
        return getUserInfo()== robotUser;
    }
}
