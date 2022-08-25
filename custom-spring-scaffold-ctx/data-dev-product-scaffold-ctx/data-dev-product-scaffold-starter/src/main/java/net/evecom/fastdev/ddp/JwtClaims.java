package net.evecom.fastdev.ddp;

import java.io.Serializable;
import java.util.Date;

public class JwtClaims implements Serializable {
    /**
     * User name
     */
    private String user_name;
    /**
     * Scope
     */
    private String[] scope;
    /**
     * Tenant id
     */
    private Long tenantId;
    /**
     * Exp
     */
    private Date exp;
    /**
     * User id
     */
    private Long userId;
    /**
     * Jti
     */
    private String jti;
    /**
     * Client id
     */
    private String client_id;
    /**
     * Username
     */
    private String username;
    /**
     * Jwt
     */
    private String jwt;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String[] getScope() {
        return scope;
    }

    public void setScope(String[] scope) {
        this.scope = scope;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
