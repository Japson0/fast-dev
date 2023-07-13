package net.evecom.fastdev.ddp;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月24日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class UserInfo implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -5742599041333505960L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 账号信息
     */
    private String username;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 是否是超管账号
     */
    private boolean admin;

    /**
     * 角色编码列表
     */
    private Set<String> roles;
    /**
     * Access key
     */
    private String tenantAccessKey;
    /**
     * Secret key
     */
    private String tenantSecretKey;
    /**
     * 是否初始化过
     */
    private boolean init;


    public UserInfo() {
    }

    public UserInfo(JwtClaims jwtClaims) {
        this.userId = jwtClaims.getUserId();
        this.tenantId = jwtClaims.getTenantId();
        this.username = jwtClaims.getUsername();
        this.clientId = jwtClaims.getClient_id();
    }

    public void init(UserResource userResource) {
        if (userResource.getRoles() != null) {
            roles = new HashSet<>(userResource.getRoles().size());
            for (RoleInfo role : userResource.getRoles()) {
                roles.add(role.getEnName());
                if (!admin && "kgap_admin".equals(role.getEnName())) {
                    admin = true;
                }
            }
        } else {
            roles = Collections.emptySet();
        }
        this.tenantAccessKey = userResource.getTenant().getAccessKey();
        this.tenantSecretKey = userResource.getTenant().getSecretKey();
        this.init = true;
    }

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
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getTenantAccessKey() {
        return tenantAccessKey;
    }

    public void setTenantAccessKey(String tenantAccessKey) {
        this.tenantAccessKey = tenantAccessKey;
    }

    public String getTenantSecretKey() {
        return tenantSecretKey;
    }

    public void setTenantSecretKey(String tenantSecretKey) {
        this.tenantSecretKey = tenantSecretKey;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

}


