package com.nlecloud.spring.webflux.scaffold.filter;

import com.nlecloud.spring.annotation.UserInfo;
import reactor.util.context.Context;
import reactor.util.context.ContextView;

import java.util.Collection;

/**
 * <P><B>用户包装类:</B></P>
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

    private Collection<String> roles;


    private static final String KEY_INFO = "USER_INFO_KEY";

    public UserWrapper(Long userId, String username,Long tenantId,Collection<String> roles) {
        this.userId = userId;
        this.username = username;
        this.tenantId=tenantId;
        this.roles=roles;
    }

    ContextView getContextView() {
        return Context.of(KEY_INFO,this);
    }


    public static UserWrapper getUserWrapper(ContextView contextView) {
        return contextView.get(KEY_INFO);
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public Collection<String> getRoles() {
        return roles;
    }

    @Override
    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }
}
