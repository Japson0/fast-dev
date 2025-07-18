
package com.nlecloud.spring.scaffold.common;


import com.nlecloud.spring.annotation.UserInfo;

import java.util.Set;

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
    private final static ThreadLocal<UserWrapper> USER_INFO_LOCAL=new ThreadLocal<>();

    private static final UserWrapper robotUser=new UserWrapper(0L,"system",0L,null);

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
        UserWrapper userWrapper = USER_INFO_LOCAL.get();
        return userWrapper==null?null:userWrapper.getUsername();
    }

    public static Long getTenantId() {
        return USER_INFO_LOCAL.get().getTenantId();
    }


    public static Set<String> getRoles(){
        return USER_INFO_LOCAL.get().getRoles();
    }

    public static UserInfo getUserInfo() {
        return USER_INFO_LOCAL.get();
    }



    public static void setUserInfo(UserWrapper userInfo){
        USER_INFO_LOCAL.set(userInfo);
    }



    /**
     * 是否包含角色
     * RevisionTrail:(Date/Author/Description)
     * 2022年10月27日
     *
     * @author Japson Huang
     */
    public static boolean hasRole(String role) {
        return USER_INFO_LOCAL.get().getRoles().contains(role);
    }


    public static boolean isRobot(){
        return getUserInfo()== robotUser;
    }
}
