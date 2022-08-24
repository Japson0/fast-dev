/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.cache.redis;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * <P><B>缓存处理器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月23日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class CacheHandle {

    /**
     * 缓存管理器
     */
    private final CacheManager cacheManager;

    public CacheHandle(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * 清除指定的typeCode
     * RevisionTrail:(Date/Author/Description)
     * 2021年11月23日
     *
     * @author Japson Huang
     */
    public void cleanDictionary(String typeCode) {
        Cache cache = cacheManager.getCache(RedisTime.DICTIONARY);
        if (cache != null) {
            cache.evict(typeCode);
        }
    }

    /**
     * 清除所有的字典表
     * RevisionTrail:(Date/Author/Description)
     * 2021年11月23日
     *
     * @author Japson Huang
     */
    public void cleanAllDictionary() {
        Cache cache = cacheManager.getCache(RedisTime.DICTIONARY);
        if (cache != null) {
            cache.clear();
        }
    }

    /**
     * 根据cacheName删除对应的key
     * RevisionTrail:(Date/Author/Description)
     * 2022年06月02日
     *
     * @author Japson Huang
     */
    public void cleanByKey(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.evict(key);
        }
    }

    /**
     * 获取对应的缓存器
     * RevisionTrail:(Date/Author/Description)
     * 2022年06月02日
     *
     * @author Japson Huang
     */
    public Cache getCache(String cacheName) {
        return cacheManager.getCache(cacheName);
    }

}
