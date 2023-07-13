package net.evecom.dolphinscheduler.cache;

import net.evecom.fastdev.cache.redis.RedisTime;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年07月11日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class CacheParamsHandle implements ParamsHandle {
    @Override
    @Cacheable(cacheNames = RedisTime.ONE_DAY, key = "#jobId")
    public String getParams(String jobId) {

        return null;
    }

    @Override
    @CachePut(cacheNames = RedisTime.ONE_DAY, key = "#jobId")
    public String putParams(String jobId, String params) {
        return params;
    }
}
