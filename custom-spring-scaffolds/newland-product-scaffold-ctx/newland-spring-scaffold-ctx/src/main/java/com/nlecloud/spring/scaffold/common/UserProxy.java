package com.nlecloud.spring.scaffold.common;

import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.annotation.enums.Sex;
import com.nlecloud.spring.scaffold.api.user.IUPMSUserApi;
import com.nlecloud.spring.scaffold.api.user.UPMSUserDTO;
import net.github.fastdev.cache.redis.RedisTime;
import net.github.fastdev.common.model.ComEnum;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserProxy {

    private final IUPMSUserApi iupmsUserApi;


    private final RedisTemplate redisTemplate;


    public UserProxy(IUPMSUserApi iupmsUserApi, RedisTemplate redisTemplate) {
        this.iupmsUserApi = iupmsUserApi;
        this.redisTemplate = redisTemplate;
    }

    @Cacheable(cacheNames = RedisTime.ONE_DAY)
    public UserInfo getUserInfo(Long userId) {
        UPMSUserDTO upmsUserDTO = iupmsUserApi.getUserDetailById(userId.toString());
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(Long.valueOf(upmsUserDTO.getId()));
        userInfo.setUsername(upmsUserDTO.getUsername());
        if(StringUtils.hasText(upmsUserDTO.getClassId())){
            userInfo.setClassId(Long.valueOf(upmsUserDTO.getClassId()));
            userInfo.setClassName(upmsUserDTO.getClassName());
        }
        if(StringUtils.hasText(upmsUserDTO.getCollegeId())){
            userInfo.setSchoolId(Long.valueOf(upmsUserDTO.getCollegeId()));
            userInfo.setSchoolName(upmsUserDTO.getCollegeName());
        }
        userInfo.setProfessionName(upmsUserDTO.getProfessionName());
        userInfo.setStudentNo(upmsUserDTO.getStudentNo());
        if(upmsUserDTO.getSex()!=null){
            userInfo.setSex(ComEnum.getEnum(upmsUserDTO.getSex(),Sex.class));
        }
        userInfo.setPhone(upmsUserDTO.getPhone());
        return userInfo;
    }

    public boolean hasApiPermission(Long userId, String apiCode) {
        return true;
    }
}
