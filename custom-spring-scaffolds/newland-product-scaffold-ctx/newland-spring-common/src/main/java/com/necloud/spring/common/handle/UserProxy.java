package com.necloud.spring.common.handle;

import com.nlecloud.spring.annotation.UserInfo;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserProxy {

    public UserInfo getUserInfo(Long userId) {
        return new UserInfo();
    }
}
