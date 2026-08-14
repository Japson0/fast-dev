package com.nlecloud.spring.scaffold.common;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import com.nlecloud.spring.annotation.OrgInfo;
import com.nlecloud.spring.annotation.TenantInfo;
import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.annotation.api.UserInfoDetail;
import com.nlecloud.spring.annotation.enums.Sex;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserWrapper implements UserInfo {

    private Long userId;

    private String username;


    private Long tenantId;

    private Long schoolId;


    private UserProxy userProxy;

    private UserInfoDetail userInfo;

    private String token;

//    public static  UserWrapper buildUserWrapper(String token,Optional<Long> tenantIdOp){
//        JSONObject claimsJson = JWT.of(token).getPayload().getClaimsJson();
//
//        String username= claimsJson.getStr("preferred_username");
//        Long userId = claimsJson.getLong("upms_id");
//        String[] tenantIds = claimsJson.getStr("tenant_id").split(",");
//        String veryCurrentTenantId = null;
//        if(tenantIdOp!=null&&tenantIdOp.isPresent()){
//            Long currentId = tenantIdOp.get();
//            if(!ArrayUtils.isEmpty(tenantIds)){
//                if(tenantIds.length==1||currentId==null){
//                    veryCurrentTenantId = tenantIds[0];
//                }else {
//                    for (String tenantId : tenantIds) {
//                        if(tenantId.equals(currentId)){
//                            veryCurrentTenantId=tenantId;
//                            break;
//                        }
//                    }
//                    veryCurrentTenantId=veryCurrentTenantId==null?tenantIds[0]:veryCurrentTenantId;
//                }
//            }
//        }else{
//            veryCurrentTenantId=tenantIds[0];
//        }
//        Long currentTenantId=Long.valueOf(veryCurrentTenantId);
//        return new UserWrapper(userId,username,currentTenantId,currentTenantId,token);
//
//    }

    public UserWrapper(Long userId, String username, Long tenantId) {
        this(userId, username, tenantId, tenantId );
    }


    public UserWrapper(Long userId, String username, Long tenantId, Long schoolId) {
        this.userId = userId;
        this.username = username;
        this.tenantId = tenantId;
        this.schoolId = schoolId;
    }


    public UserWrapper(Long userId, String username, Long tenantId, Long schoolId, String token) {
        this.userId = userId;
        this.username = username;
        this.tenantId = tenantId;
        this.schoolId = schoolId;
        this.token = token;
    }


    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public Long getTenantId() {
        return tenantId;
    }

    @Override
    public Long getSchoolId() {
        return schoolId;
    }


    @Override
    public Set<String> getRoles() {
        Map<Long, Set<String>> tenantRoleCodeMap = getUserInfo().getTenantRoleCodeMap();
        if(CollectionUtils.isEmpty(tenantRoleCodeMap)){
            return Collections.EMPTY_SET;
        }
        return tenantRoleCodeMap.getOrDefault(tenantId,Collections.EMPTY_SET);
    }


    @Override
    public String getEmail() {
        return getUserInfo().getEmail();
    }


    @Override
    public Sex getSex() {
        return getUserInfo().getSex();
    }


    @Override
    public String getPhone() {
        return getUserInfo().getPhone();
    }

    @Override
    public boolean isPhoneVerify() {
        return getUserInfo().isPhoneVerify();
    }


    @Override
    public OrgInfo getOrgInfo() {
        Map<Long, OrgInfo> orgInfos = getUserInfo().getOrgInfos();
        if(CollectionUtils.isEmpty(orgInfos)){
            return null;
        }
        return orgInfos.get(this.tenantId);
    }

    @Override
    public TenantInfo getTenantInfo() {
        for (TenantInfo tenantInfo : getUserInfo().getTenantList()) {
            if (tenantInfo.getId().equals(this.tenantId)) {
                return tenantInfo;
            }
        }
        return null;
    }

    @Override
    public List<Long> getManagerOrges() {
        Map<Long, List<Long>> tenantOrg = getUserInfo().getTenantOrg();
        if(CollectionUtils.isEmpty(tenantOrg)){
            return Collections.EMPTY_LIST;
        }
        return tenantOrg.get(this.tenantId);
    }

    @Override
    public String getNickName() {
        return getUserInfo().getNickName();
    }


    private UserInfoDetail getUserInfo() {
        if (userInfo == null) {
            this.userInfo = token != null ? checkUserProxy().getUserInfo(this.userId, token.substring("Bearer ".length())) : checkUserProxy().getUserInfo(this.userId);
        }
        return userInfo;
    }



    private UserProxy checkUserProxy() {
        if (userProxy == null) {
            synchronized (this) {
                if (userProxy == null) {
                    userProxy = SpringUtil.getBean(UserProxy.class);
                }
            }
        }
        return userProxy;
    }
}
