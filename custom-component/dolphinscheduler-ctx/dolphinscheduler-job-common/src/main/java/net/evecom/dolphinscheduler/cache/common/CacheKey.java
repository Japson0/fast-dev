package net.evecom.dolphinscheduler.cache.common;

/**
 * <P><B>缓存KEY:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年07月20日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class CacheKey {

    /**
     * 缓存KEY
     */
    public static final String CACHE_KEY = "JOB_PARAMS_KEY_%s";

    /**
     * 缓存过期时间
     */
    public static final long EXPIRED_TIME = 3600L;

    public static String getCacheKey(String jobId) {
        return String.format(CACHE_KEY, jobId);
    }
}
