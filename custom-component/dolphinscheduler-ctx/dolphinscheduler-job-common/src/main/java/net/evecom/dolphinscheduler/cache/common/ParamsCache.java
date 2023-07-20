package net.evecom.dolphinscheduler.cache.common;

/**
 * <P><B>缓存类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年07月20日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface ParamsCache {

    String get(String key);

    void put(String key, String params);
}
