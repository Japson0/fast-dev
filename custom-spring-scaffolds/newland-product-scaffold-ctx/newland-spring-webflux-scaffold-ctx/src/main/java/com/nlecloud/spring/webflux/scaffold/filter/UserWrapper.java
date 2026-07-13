package com.nlecloud.spring.webflux.scaffold.filter;

import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.annotation.UserInfoImpl;
import com.nlecloud.spring.webflux.scaffold.user.UserProxy;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import reactor.util.context.ContextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserWrapper  {

    private Long userId;

    private String username;


    private Long tenantId;

    private Long schoolId;

    private Collection<String> roles;


    private UserProxy userProxy;

    private Mono<UserInfoImpl> userInfo;

    private String token;


    private static final String KEY_INFO = "USER_INFO_KEY";

    public ContextView getContextView() {
        return Context.of(KEY_INFO,this);
    }


    public static UserWrapper getUserWrapper(ContextView contextView) {
        return contextView.getOrDefault(KEY_INFO,null);
    }


//    public UserWrapper(HttpHeaders headers){
//        String userId = headers.getFirst("x-userid-header");
//        String username = headers.getFirst("x-user-header");
//        String roles = headers.getFirst("x-role-header");
//        String tenantId = headers.getFirst("x-school-header");
//
//        if(userId!=null) {
//            this.userId=Long.valueOf(userId);
//            this.username=username;
//            //TODO 租户这里有可能没有
//            this.tenantId=tenantId.split(",");
//            if (roles != null) {
//                this.roles = Collections.EMPTY_SET;
//            } else {
//                String[] rolesSplit = roles.split(",");
//                this.roles = new HashSet<>(rolesSplit.length);
//                for (String role : rolesSplit) {
//                    this.roles.add(role);
//                }
//            }
//        }
//    }

    public UserWrapper(Long userId, String username,Long tenantId) {
        this(userId,username,tenantId,tenantId, Collections.EMPTY_SET);
    }

    public UserWrapper(Long userId,String username,Long schoolId,Collection<String> roles) {
        this(userId,username, schoolId,schoolId,roles);
    }

    public UserWrapper(Long userId,String username,Long tenantId,Long schoolId,Collection<String> roles) {
        this.userId = userId;
        this.username = username;
        this.tenantId=tenantId;
        this.schoolId=schoolId;
        this.roles=roles;
    }


    public UserWrapper(Long userId,String username,Long tenantId,Long schoolId,Collection<String> roles,String token) {
        this.userId = userId;
        this.username = username;
        this.tenantId=tenantId;
        this.schoolId=schoolId;
        this.roles=roles;
        this.token=token;
    }


    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }


    public Long getTenantId() {
        return tenantId;
    }



    public Long getSchoolId() {
        return this.schoolId;
    }


    public Collection<String> getRoles() {
        return this.roles;
    }


    public  boolean isAdmin(){
        return getRoles().contains("admin");
    }

    /**
     *是否是租户管理员
     *RevisionTrail:(Date/Author/Description)
     * 2026年05月27日
     *@author Japson Huang
     *
     */
    public  Mono<Boolean> isTenantAdmin(){
        return getUserInfoImpl().map(user -> !CollectionUtils.isEmpty(user.getAdminTenant())
                && user.getAdminTenant().contains(getTenantId()));
    }

    /**
     *是否机构管理员
     *RevisionTrail:(Date/Author/Description)
     * 2026年05月29日
     *@author Japson Huang
     *
     */
    public  Mono<Boolean> isOrgAdmin(){
        return getManagerOrges().map(orges->!orges.isEmpty());
    }

    public Mono<Long> getOrgId() {
        return getManagerOrges().map(orges->orges.isEmpty()?null: orges.get(0));
    }

    public Mono<List<Long>> getManagerOrges(){
        return getUserInfoImpl().map(user->{
            if(this.tenantId==null||user.getTenantOrg().isEmpty()){
                return Collections.EMPTY_LIST;
            }
            List<Long> orges = user.getTenantOrg().get(this.tenantId);
            return orges==null?Collections.EMPTY_LIST:orges;
        });

    }

    public Mono<UserInfo> getUserInfo(){
        return getUserInfoImpl().cast(UserInfo.class);
    }

    private Mono<UserInfoImpl> getUserInfoImpl() {
        if(userInfo == null){
              this.userInfo = StringUtils.startsWithIgnoreCase(token, "Bearer ")
                      ? checkUserProxy().getUserInfo(this.userId, token.substring("Bearer ".length())).cache()
                      : checkUserProxy().getUserInfo(this.userId).cache(); //这里通过远程调用获取用户信息
        }
        return userInfo;
    }


    private UserProxy checkUserProxy(){
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
