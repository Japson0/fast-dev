package net.evecom.fastdev.ddp;

import java.io.Serializable;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月24日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserInfo implements Serializable {


    /**
     * User id
     */
    private Long userId;
    /**
     * Username
     */
    private String username;

    /**
     * 租户
     */
    private Long tenantId;

    /**
     * 是否初始化过
     */
    private boolean init;


    public UserInfo(Long userId, Long tenantId) {
        this.userId = userId;
        this.tenantId = tenantId;
    }

    public void init(JwtClaims jwtClaims) {
        this.username = jwtClaims.getUsername();
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

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }
}
