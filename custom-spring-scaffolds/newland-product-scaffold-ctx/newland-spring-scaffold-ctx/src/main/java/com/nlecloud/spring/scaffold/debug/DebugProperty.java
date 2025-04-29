package com.nlecloud.spring.scaffold.debug;

import com.nlecloud.spring.annotation.UserInfo;

/**
 * <P><B>debug:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class DebugProperty {

    private boolean logger = false;


    private boolean injectUser = false;

    private UserInfo userInfo;

    public boolean isLogger() {
        return logger;
    }

    public void setLogger(boolean logger) {
        this.logger = logger;
    }

    public boolean isInjectUser() {
        return injectUser;
    }

    public void setInjectUser(boolean injectUser) {
        this.injectUser = injectUser;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
