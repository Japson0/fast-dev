package com.nlecloud.spring.scaffold.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * <P><B>用户信息:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Getter
@Setter
public class UserInfo implements Serializable {

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
     * 角色编码列表
     */
    private Set<String> roles;

    /**
     * 班级ID
     */
    private String classId;

    /**
     * 又想
     */
    private String email;

}
