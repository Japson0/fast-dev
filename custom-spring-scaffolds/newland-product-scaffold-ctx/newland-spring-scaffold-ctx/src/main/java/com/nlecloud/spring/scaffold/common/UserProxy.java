package com.nlecloud.spring.scaffold.common;

import cn.hutool.core.io.IoUtil;
import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.annotation.enums.Sex;
import com.nlecloud.spring.scaffold.api.user.IUPMSUserApi;
import com.nlecloud.spring.scaffold.api.user.UPMSUserDTO;
import net.github.fastdev.cache.redis.RedisTime;
import net.github.fastdev.common.model.ComEnum;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

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


    private final RedisTemplate<String, String> redisTemplate;


    public UserProxy(IUPMSUserApi iupmsUserApi, RedisTemplate redisTemplate) {
        this.iupmsUserApi = iupmsUserApi;
        this.redisTemplate = redisTemplate;
//        init();
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



    private String scriptSha;

    private final String SCRIPT_KEY = "api_permission:API_PERMISSION_SCRIPT_KEY";

    // RedisSerializer 用于序列化 key/value
    private final StringRedisSerializer serializer = new StringRedisSerializer();

    private void init() {
        this.scriptSha = redisTemplate.opsForValue().get(SCRIPT_KEY);
        if (scriptSha == null) {
            reload();
        }
    }


    private String reload() {
        ClassPathResource resource = new ClassPathResource("script/contain_api.lua"); // 如 "scripts/redis.lua"
        try (InputStream is = resource.getInputStream()) {
            String script = IoUtil.read(is, StandardCharsets.UTF_8);
            return redisTemplate.execute((RedisCallback<String>) connection -> {
                        this.scriptSha = connection.scriptLoad(serializer.serialize(script));
                        connection.set(SCRIPT_KEY.getBytes(StandardCharsets.UTF_8), serializer.serialize(scriptSha));
                        return null;
                    }
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Boolean containsValue(Collection<String> keys, String value) {
        return redisTemplate.execute((RedisConnection connection) -> {
            try {
                // 序列化：keys 和 args
                byte[][] keysAndArgs = new byte[keys.size() + 1][];

                int pos=0;
                for (String key : keys) {
                    keysAndArgs[pos++]=serializer.serialize(key);
                }

                // 最后一个是 value (ARGV[1])
                keysAndArgs[keys.size()] = serializer.serialize(value);

                // 执行 EVALSHA
                Long result = (Long) connection.evalSha(
                        scriptSha.getBytes(StandardCharsets.UTF_8),  // SHA 字符串转 byte[]
                        ReturnType.INTEGER,                           // 返回类型
                        keys.size(),                                  // numKeys
                        keysAndArgs                                   // keys + args
                );

                return result != null && result == 1L;

            } catch (Exception e) {
                // 如果脚本未找到（Redis 重启），重新加载
                if (e.getMessage() != null && e.getMessage().contains("NOSCRIPT")) {
                    reload();
                    // 重试
                    return containsValue(keys, value);
                }
                throw e;
            }
        });
    }
}
