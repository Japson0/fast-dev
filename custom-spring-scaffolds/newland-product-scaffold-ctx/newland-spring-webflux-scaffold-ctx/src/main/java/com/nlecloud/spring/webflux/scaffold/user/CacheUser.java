package com.nlecloud.spring.webflux.scaffold.user;

import com.nlecloud.spring.annotation.api.UserInfoDetail;
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

    private UserInfoDetail userInfo;

    private long exp;

    private long iat;

    public CacheUser() {
    }

    public CacheUser(UserInfoDetail userInfo) {
        this.userInfo = userInfo;
    }

    public CacheUser(UserInfoDetail userInfo, long iat, long exp ) {
        this.userInfo = userInfo;
        this.exp = exp;
        this.iat = iat;
    }
}
