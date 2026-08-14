package com.nlecloud.spring.webflux.scaffold.filter;

import cn.hutool.extra.spring.SpringUtil;
import com.nlecloud.spring.annotation.TenantInfo;
import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.annotation.UserInfoImpl;
import com.nlecloud.spring.annotation.api.UserInfoDetail;
import com.nlecloud.spring.webflux.scaffold.user.UserProxy;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import reactor.util.context.ContextView;

import java.util.*;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserWrapper {

    private Long userId;

    private String username;


    private Long tenantId;

    private Long schoolId;

    private Collection<String> roles;

    private UserProxy userProxy;

    private Mono<UserInfo> cachedUserInfo;

    private String token;


    private static final String KEY_INFO = "USER_INFO_KEY";

    public ContextView getContextView() {
        return Context.of(KEY_INFO, this);
    }


    public static UserWrapper getUserWrapper(ContextView contextView) {
        return contextView.getOrDefault(KEY_INFO, null);
    }


    public UserWrapper(Long userId, String username, Long tenantId, Long schoolId) {
        this.userId = userId;
        this.username = username;
        this.tenantId = tenantId;
        this.schoolId = schoolId;
        this.roles = roles;
    }


    public UserWrapper(Long userId, String username, Long tenantId, Long schoolId, String token) {
        this.userId = userId;
        this.username = username;
        this.tenantId = tenantId;
        this.schoolId = schoolId;
        this.roles = roles;
        this.token = token;
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


    public Mono<Boolean> isAdmin() {
        return getUserInfo().map(user -> user.getRoles().contains("admin"));
    }

    /**
     * 是否是租户管理员
     * RevisionTrail:(Date/Author/Description)
     * 2026年05月27日
     *
     * @author Japson Huang
     */
    public Mono<Boolean> isTenantAdmin() {

        return getUserInfo().map(user -> user.getTenantInfo()==null?false:user.getTenantInfo().isAdmin());
    }

    /**
     * 是否机构管理员
     * RevisionTrail:(Date/Author/Description)
     * 2026年05月29日
     *
     * @author Japson Huang
     */
    public Mono<Boolean> isOrgAdmin() {
        return getUserInfo().map(user -> {
            if (user.getOrgInfo() == null) {
                return null;
            }
            return user.getOrgInfo().isAdmin();
        });
    }

    public Mono<Long> getOrgId() {
        return getUserInfo().map(user -> {
            if (user.getOrgInfo() == null) {
                return null;
            }
            return user.getOrgInfo().getId();
        });
    }


    public Mono<List<Long>> getManagerOrges() {
        return getUserInfo().map(user -> {
            if (CollectionUtils.isEmpty(user.getManagerOrges())) {
                return Collections.EMPTY_LIST;
            }
            return user.getManagerOrges();
        });
    }

    public Mono<UserInfo> getUserInfo() {
        if (cachedUserInfo == null) {
            this.cachedUserInfo = getUserInfoImpl().map(userInfoDetail -> {
                        List<TenantInfo> tenantList = userInfoDetail.getTenantList();
                        TenantInfo currentTenant = null;
                        if (CollectionUtils.isEmpty(tenantList)) {
                            for (TenantInfo tenantInfo : tenantList) {
                                if (tenantInfo.getId().equals(this.tenantId)) {
                                    currentTenant = tenantInfo;
                                    break;
                                }
                            }
                        }
                        return UserInfoImpl.builder()
                                .userId(this.userId)
                                .username(this.username)
                                .nickName(userInfoDetail.getNickName())
                                .schoolId(this.schoolId)
                                .tenantId(tenantId)
                                .schoolName(userInfoDetail.getSchoolName())
                                .roles(userInfoDetail.getTenantRoleCodeMap() != null && this.tenantId != null
                                        ? userInfoDetail.getTenantRoleCodeMap().get(this.tenantId)
                                        : (userInfoDetail.getRoles() != null ? new HashSet<>(userInfoDetail.getRoles()) : null))
                                .classId(userInfoDetail.getClassId())
                                .className(userInfoDetail.getClassName())
                                .studentNo(userInfoDetail.getStudentNo())
                                .professionName(userInfoDetail.getProfessionName())
                                .email(userInfoDetail.getEmail())
                                .avatar(userInfoDetail.getAvatar())
                                .sex(userInfoDetail.getSex())
                                .phone(userInfoDetail.getPhone())
                                .phoneVerify(userInfoDetail.isPhoneVerify())
                                .tenantInfo(currentTenant)
                                .orgInfo(userInfoDetail.getOrgInfos() != null && this.tenantId != null
                                        ? userInfoDetail.getOrgInfos().get(this.tenantId) : null)
                                .managerOrges(userInfoDetail.getTenantOrg() != null && this.tenantId != null
                                        ? userInfoDetail.getTenantOrg().get(this.tenantId) : null)
                                .build();
                    }
            ).cast(UserInfo.class).cache();

        }
        return cachedUserInfo;
    }


    private Mono<UserInfoDetail> getUserInfoImpl() {
        return StringUtils.startsWithIgnoreCase(token, "Bearer ")
                ? checkUserProxy().getUserInfo(this.userId, token.substring("Bearer ".length()))
                : checkUserProxy().getUserInfo(this.userId);
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
