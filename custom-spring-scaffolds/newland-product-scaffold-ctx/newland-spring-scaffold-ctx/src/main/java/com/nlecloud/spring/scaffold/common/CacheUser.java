package com.nlecloud.spring.scaffold.common;

import com.nlecloud.spring.annotation.UserInfo;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年02月26日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class CacheUser extends UserInfo {

    private long exp;

    private long iat;

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public Long getIat() {
        return iat;
    }

    public void setIat(Long iat) {
        this.iat = iat;
    }
}
