package net.evecom.dolphinscheduler.cache;

import net.evecom.dolphinscheduler.cache.common.AbstractParamsCache;
import net.evecom.dolphinscheduler.cache.common.CacheKey;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年07月20日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class SpringCache extends AbstractParamsCache {

    /**
     * redis缓存
     */
    private final RedisTemplate redisTemplate;

    public SpringCache(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected String getParams(String key) {
        Object params = redisTemplate.boundGeoOps(key).getKey();
        if (params != null) {
            return params.toString();
        }
        return null;
    }

    @Override
    protected void putParams(String key, String params, long expired) {
        redisTemplate.boundValueOps(key).set(params, CacheKey.EXPIRED_TIME, TimeUnit.SECONDS);
    }

}
