package com.nlecloud.spring.scaffold.common;

import com.nlecloud.spring.annotation.UserInfoImpl;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <P><B>缓存用户类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年02月26日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Getter
@Setter
public class CacheUser implements Serializable {

    private UserInfoImpl userInfo;

    public CacheUser() {
    }

    private long exp;

    private long iat;

    public CacheUser(UserInfoImpl userInfo) {
        this.userInfo = userInfo;
    }

    public CacheUser(UserInfoImpl userInfo, long iat, long exp ) {
        this.userInfo = userInfo;
        this.exp = exp;
        this.iat = iat;
    }
}
