package com.nlecloud.spring.scaffold.common;

import cn.hutool.extra.spring.SpringUtil;
import com.nlecloud.spring.scaffold.service.UserProxy;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserWrapper extends UserInfo{

    private Long userId;

    private String username;

    private UserInfo userInfo;

    private  static UserProxy userProxy;


    public UserWrapper(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public UserInfo getUserInfo() {
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
