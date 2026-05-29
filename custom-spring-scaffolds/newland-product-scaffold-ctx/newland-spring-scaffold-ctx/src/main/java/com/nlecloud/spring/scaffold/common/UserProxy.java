package com.nlecloud.spring.scaffold.common;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.RegisteredPayload;
import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.annotation.api.UserInfoService;
import com.nlecloud.spring.annotation.enums.Sex;
import com.nlecloud.spring.annotation.api.UPMSUserDTO;
import net.github.fastdev.boot.utils.JacksonUtils;
import net.github.fastdev.common.model.ComEnum;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserProxy {

    private final UserInfoService userinfoService;

    private static final String USER_KEY = "USER_INFO:%d";

    private final RedisTemplate<String, String> redisTemplate;


    public UserProxy(UserInfoService userinfoService, RedisTemplate redisTemplate) {
        this.userinfoService = userinfoService;
        this.redisTemplate = redisTemplate;
    }

    public UserInfo getUserInfo(Long userId, String jwtToken) {
        String cacheKey = String.format(USER_KEY, userId);
        String userJson = redisTemplate.opsForValue().get(cacheKey);
        JWT jwt = JWTUtil.parseToken(jwtToken);
        long iat =((Number) jwt.getPayload(RegisteredPayload.ISSUED_AT)).longValue();
        long exp =((Number) jwt.getPayload(RegisteredPayload.EXPIRES_AT)).longValue();
        if (StringUtils.hasText(userJson)) {
            CacheUser cacheUser = JacksonUtils.toBean(userJson, CacheUser.class);
            return iat>cacheUser.getIat()? cacheUser(cacheKey, userId, iat, exp) : cacheUser;
        } else {
            return cacheUser(cacheKey, userId, iat, exp);
        }
    }

    public UserInfo getUserInfo(Long userId) {
        String cacheKey = String.format(USER_KEY, userId);
        String userJson = redisTemplate.opsForValue().get(cacheKey);
        if (userJson != null) {
            return JacksonUtils.toBean(userJson, CacheUser.class);
        } else {
            CacheUser remoteUserInfo = getRemoteUserInfo(userId);
            redisTemplate.opsForValue().set(cacheKey, JacksonUtils.toJson(remoteUserInfo), Duration.ofDays(1));
            return remoteUserInfo;
        }
    }

    private UserInfo cacheUser(String cacheKey, Long userId, Long iat, Long exp) {
        CacheUser remoteUserInfo = getRemoteUserInfo(userId);
        remoteUserInfo.setIat(iat);
        remoteUserInfo.setExp(exp);
        redisTemplate.opsForValue().set(cacheKey, JacksonUtils.toJson(remoteUserInfo), Duration.of(exp - iat, ChronoUnit.SECONDS));
        return remoteUserInfo;
    }


    private CacheUser getRemoteUserInfo(Long userId) {
        UPMSUserDTO upmsUserDTO = userinfoService.getUserDetailById(userId.toString());
        CacheUser userInfo = new CacheUser();
        userInfo.setUserId(Long.valueOf(upmsUserDTO.getId()));
        userInfo.setUsername(upmsUserDTO.getUsername());
        if (StringUtils.hasText(upmsUserDTO.getClassId())) {
            userInfo.setClassId(Long.valueOf(upmsUserDTO.getClassId()));
            userInfo.setClassName(upmsUserDTO.getClassName());
        }
        if (StringUtils.hasText(upmsUserDTO.getCollegeId())) {
            userInfo.setSchoolId(Long.valueOf(upmsUserDTO.getCollegeId()));
            userInfo.setSchoolName(upmsUserDTO.getCollegeName());
        }
        userInfo.setProfessionName(upmsUserDTO.getProfessionName());
        userInfo.setNickName(upmsUserDTO.getName());
        userInfo.setStudentNo(upmsUserDTO.getStudentNo());
        if (upmsUserDTO.getSex() != null) {
            userInfo.setSex(ComEnum.getEnum(upmsUserDTO.getSex(), Sex.class));
        }
        userInfo.setPhone(upmsUserDTO.getPhone());
        userInfo.setTenantOrg(upmsUserDTO.getOrgAdminOrgIds());
        userInfo.setAdminTenant(upmsUserDTO.getTenantAdminTenantIds());
        return userInfo;
    }
}
