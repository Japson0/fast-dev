package com.necloud.spring.common.handle;

import cn.hutool.extra.spring.SpringUtil;
import com.nlecloud.spring.annotation.UserInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServerHttpRequest;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

    private Set<String> roles;

    private Long tenantId;

    private  static UserProxy userProxy;

    private UserInfo userInfo;


    public UserWrapper(HttpHeaders headers){
        String userId = headers.getFirst("x-userid-header");
        String username = headers.getFirst("x-user-header");
        String roles = headers.getFirst("x-role-header");
        String tenantId = headers.getFirst("x-school-header");

        if(userId!=null) {
            this.userId=Long.valueOf(userId);
            this.username=username;
            //TODO 租户这里有可能没有
            this.tenantId=Long.valueOf(tenantId);
            if (roles != null) {
                this.roles = Collections.EMPTY_SET;
            } else {
                String[] rolesSplit = roles.split(",");
                this.roles = new HashSet<>(rolesSplit.length);
                for (String role : rolesSplit) {
                    this.roles.add(role);
                }
            }
        }
    }



    public UserWrapper(Long userId, String username,Long tenantId, Set<String> roles) {
        this.userId = userId;
        this.username = username;
        this.roles = roles;
        this.tenantId=tenantId;
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
    public Set<String> getRoles() {
        return roles;
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
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String getClassId() {
        return getUserInfo().getClassId();
    }

    @Override
    public String getEmail() {
        return getUserInfo().getEmail();
    }

    private UserInfo getUserInfo() {
        if(userInfo == null){
            if(userProxy == null){
                synchronized (this){
                    if(userProxy == null){
                        userProxy=SpringUtil.getBean(UserProxy.class);
                    }
                }
            }
            this.userInfo=userProxy.getUserInfo(this.userId); //这里通过远程调用获取用户信息
        }
        return this.userInfo;
    }

}
