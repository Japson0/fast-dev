/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.evecom.fastdev.cache.redis.CacheHandle;
import net.evecom.fastdev.common.model.RestResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <P><B>缓存控制层:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月23日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@RequestMapping("cacheManage")
@ResponseBody
@Api(tags = "缓存操作")
public class CacheController {

    /**
     * 缓存处理器
     */
    private final CacheHandle cacheHandle;

    public CacheController(CacheHandle cacheHandle) {
        this.cacheHandle = cacheHandle;
    }

    @DeleteMapping("{typeCode}")
    @ApiOperation(value = "删除某类字典缓存", notes = "只会删除RedisTime.Dictionary缓存名称中的信息")
    public RestResponse cleanDictionary(@PathVariable String typeCode) {
        cacheHandle.cleanDictionary(typeCode);
        return RestResponse.renderSuccess();
    }

    @DeleteMapping
    @ApiOperation(value = "删除全部字典表缓存", notes = "只会删除RedisTime.Dictionary缓存名称中的信息")
    public RestResponse cleanAllDictionary() {
        cacheHandle.cleanAllDictionary();
        return RestResponse.renderSuccess();
    }
}
