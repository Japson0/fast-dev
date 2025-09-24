
package com.nlecloud.spring.scaffold.common;


import com.nlecloud.spring.annotation.UserInfo;

import java.util.Collection;

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
