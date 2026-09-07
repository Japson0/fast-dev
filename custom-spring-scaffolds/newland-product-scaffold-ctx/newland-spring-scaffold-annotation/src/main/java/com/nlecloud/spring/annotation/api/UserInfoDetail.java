package com.nlecloud.spring.annotation.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nlecloud.spring.annotation.OrgInfo;
import com.nlecloud.spring.annotation.TenantInfo;
import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.annotation.enums.Sex;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.*;

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
public class UserInfoDetail implements Serializable {

    /**
     * 序列化号
     */
    private static final long serialVersionUID = 0L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 账号信息
     */
    private String username;

    /**
     * 用户昵称
     */
    @JsonProperty("name")
    private String nickName;


    /**
     * 学校ID
     */
    private Long schoolId;

    /**
     * tenantId
     */
    private Long tenantId;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 角色编码列表
     */
    @Deprecated
    private transient Collection<String> roles;

    /**
     * 角色列表
     */
    private Map<Long, Set<String>> tenantRoleCodeMap;
    /**
     * 班级ID
     */
    private Long classId;

    /**
     * 帮班级名称
     */
    private String className;

    /**
     * 学号
     */
    private String studentNo;

    /**
     * 专业名称
     */
    private String professionName;
    /**
     * 又想
     */
    private String email;

    /**
     * 头像url
     */
    private String avatar;

    /**
     * 性别
     */
    private Sex sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 手机号是否验证
     */
    @JsonProperty("realNameVerified")
    private boolean phoneVerify;


    /**
     * 机构管理员的机构ID列表
     */
    @JsonProperty("orgAdminOrgIds")
    private Map<Long,List<Long>> tenantOrg;

    /**
     * 租户列表
     */
    private List<TenantInfo> tenantList;

    /**
     *租户机构信息
     *RevisionTrail:(Date/Author/Description)
     * 2026年07月29日
     *@author Japson Huang
     *
    */
    @JsonProperty("orges")
    private Map<Long, OrgInfo> orgInfos;

}
