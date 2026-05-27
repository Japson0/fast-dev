
package com.nlecloud.spring.scaffold.common;


import com.nlecloud.spring.annotation.UserInfo;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

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

    @Deprecated
    public static Long getSchoolId(){
        return getUserInfo().getSchoolId();
    }


    public static Collection<String> getRoles(){
        return getUserInfo().getRoles();
    }

    public static UserInfo getUserInfo() {
        return USER_INFO_LOCAL.get();
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
        if(CollectionUtils.isEmpty(getUserInfo().getAdminTenant())){
            return false;
        }
        return getUserInfo().getAdminTenant().contains(getTenantId());
    }

    /**
     *是否是组织管理员
     *RevisionTrail:(Date/Author/Description)
     * 2026年05月27日
     *@author Japson Huang
     *
    */
    public static Optional<Long> hasOrgAdmin(){
        if(CollectionUtils.isEmpty(getUserInfo().getAdminOrg())){
            return Optional.empty();
        }
        return Optional.ofNullable(getUserInfo().getAdminOrg().get(getTenantId()));
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
