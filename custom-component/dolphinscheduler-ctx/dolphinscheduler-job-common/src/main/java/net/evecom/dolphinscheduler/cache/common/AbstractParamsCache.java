package net.evecom.dolphinscheduler.cache.common;

/**
 * <P><B>参数获取抽象类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年07月20日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public abstract class AbstractParamsCache implements ParamsCache {

    protected abstract String getParams(String key);

    protected abstract void putParams(String key, String params, long expired);

    @Override
    public String get(String key) {
        return getParams(generatorKey(key));
    }

    @Override
    public void put(String key, String params) {
        putParams(generatorKey(key), params, CacheKey.EXPIRED_TIME);
    }

    private String generatorKey(String key) {
        return String.format(CacheKey.CACHE_KEY, key);
    }
}
