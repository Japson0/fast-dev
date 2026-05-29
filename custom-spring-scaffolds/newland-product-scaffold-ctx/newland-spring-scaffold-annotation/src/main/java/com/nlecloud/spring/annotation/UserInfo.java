package com.nlecloud.spring.annotation;

import com.nlecloud.spring.annotation.enums.Sex;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

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
@SuperBuilder
@Getter
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
    private Collection<String> roles;


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

    private String phone;


    /**
     * 租户管理员的租户ID列表
     */
    private Set<Long> adminTenant;

    /**
     * 机构管理员的机构ID列表
     */
    private Map<Long,List<Long>> tenantOrg;


    public Long getOrgId(){
        return getManagerOrges().get(0);
    }

    public List<Long> getManagerOrges(){
        if(getTenantId()==null){
            return Collections.EMPTY_LIST;
        }
        return getTenantOrg().get(getTenantId());
    }

}
