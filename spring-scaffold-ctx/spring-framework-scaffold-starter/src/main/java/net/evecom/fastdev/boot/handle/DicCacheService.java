/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.handle;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * <P><B>缓存字典表:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月13日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public abstract class DicCacheService implements DictionaryService, ResourceClean, ApplicationRunner {

    /**
     * 本地缓存
     */
    private final ThreadLocal<Map<String, Map<String, String>>> cache = ThreadLocal.withInitial(() -> new HashMap<>(5));

    /**
     * 自身代理类
     */
    protected DicCacheService proxyService;

    @Override
    public String getDicStr(String typeCode, String code) {
        Map<String, Map<String, String>> codeMap = cache.get();
        Map<String, String> typeCodeMap = codeMap.get(typeCode);
        if (typeCodeMap == null) {
            Map<String, String> dicDetail = proxyService.getDicDetail(typeCode);
            if (dicDetail == null) {
                return null;
            }
            codeMap.put(typeCode, dicDetail);
            typeCodeMap = dicDetail;
        }
        return typeCodeMap.get(code);
    }

    /**
     * 获取
     * RevisionTrail:(Date/Author/Description)
     * 2021年11月13日
     *
     * @author Japson Huang
     */
    protected abstract Map<String, String> getDicDetail(String typeCode);

    /**
     * 拦截器会自动调用此方法，无需手动调用
     * RevisionTrail:(Date/Author/Description)
     * 2021年11月13日
     *
     * @author Japson Huang
     */
    @Override
    public void clean() {
        cache.remove();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        proxyService = SpringUtil.getBean(DicCacheService.class);
    }
}
