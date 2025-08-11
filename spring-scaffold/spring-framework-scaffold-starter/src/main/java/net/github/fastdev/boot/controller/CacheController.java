

package net.github.fastdev.boot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.github.fastdev.cache.redis.CacheHandle;
import net.github.fastdev.common.model.RestResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <P><B>缓存控制层:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月23日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
//@RequestMapping("cacheManage")
//@ResponseBody
@Tag(name = "缓存操作")
public class CacheController {

    /**
     * 缓存处理器
     */
    private final CacheHandle cacheHandle;

    public CacheController(CacheHandle cacheHandle) {
        this.cacheHandle = cacheHandle;
    }

    @DeleteMapping("{typeCode}")
    @Operation(summary = "删除某类字典缓存", description = "只会删除RedisTime.Dictionary缓存名称中的信息")
    public RestResponse cleanDictionary(@PathVariable String typeCode) {
        cacheHandle.cleanDictionary(typeCode);
        return RestResponse.renderSuccess();
    }

    @DeleteMapping
    @Operation(summary = "删除全部字典表缓存", description = "只会删除RedisTime.Dictionary缓存名称中的信息")
    public RestResponse cleanAllDictionary() {
        cacheHandle.cleanAllDictionary();
        return RestResponse.renderSuccess();
    }
}
