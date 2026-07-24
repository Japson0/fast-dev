package com.nlecloud.spring.annotation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nlecloud.spring.annotation.enums.Sex;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.*;

/**
 * <P><B>用户信息:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@SuperBuilder
@Getter
@Jacksonized
public class UserInfoImpl implements UserInfo {

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
    private Collection<String> roles;

    /**
     * 角色列表
     */
    private Map<String, Collection<String>> tenantRoleCodeMap;
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
     * 租户管理员的租户ID列表
     */
    @JsonProperty("tenantAdminTenantIds")
    private Set<Long> adminTenant=Collections.EMPTY_SET;

    /**
     * 机构管理员的机构ID列表
     */
    @JsonProperty("orgAdminOrgIds")
    private Map<Long,List<Long>> tenantOrg=Collections.EMPTY_MAP;


    @Override
    @JsonIgnore
    public Long getOrgId() {
        List<Long> managerOrges = getManagerOrges();
        return managerOrges.isEmpty()?null:managerOrges.get(0);
    }

    @Override
    @JsonIgnore
    public List<Long> getManagerOrges(){
        if(getTenantId()==null||getTenantOrg().isEmpty()){
            return Collections.EMPTY_LIST;
        }
        List<Long> orges = getTenantOrg().get(getTenantId());
        return orges==null?Collections.EMPTY_LIST:orges;
    }

    @Override
    public Collection<String> getRoles() {
        if(tenantRoleCodeMap==null){
            //兼容旧版本
            return roles;
        }
        return tenantRoleCodeMap.get(getTenantId());
    }
}
