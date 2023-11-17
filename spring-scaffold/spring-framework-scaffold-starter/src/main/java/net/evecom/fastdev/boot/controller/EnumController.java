/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.evecom.fastdev.common.model.RestResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <P><B>枚举拆线呢类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年07月27日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@RequestMapping("enums")
@Api(tags = "枚举类查询")
public class EnumController {

    /**
     * 字典名称
     */
    private final Map<String, Class<?>> nameMap = new ConcurrentHashMap<>();

    /**
     * 前缀包
     */
    private final String prefixPackage;

    public EnumController(String prefixPackage) {
        if (prefixPackage != null) {
            this.prefixPackage = prefixPackage + ".";
        } else {
            this.prefixPackage = null;
        }
    }

    @GetMapping
    @ApiOperation(value = "根据全路径查询枚举", notes = "name是枚举类型的类全路径名称")
    @ResponseBody
    private RestResponse enumByAllName(@RequestParam("name") String name) {
        try {
            Class<?> aClass = nameMap.get(name);
            if (aClass == null) {
                aClass = Class.forName(name);
                nameMap.put(name, aClass);
            }
            return RestResponse.renderSuccess(aClass.getEnumConstants());
        } catch (ClassNotFoundException e) {
            return RestResponse.renderUserResourceError("不存在对应的字典信息");
        }
    }

    @PostMapping("batch")
    @ResponseBody
    public RestResponse enumByAllNames(@RequestBody List<String> names) {
        try {
            Map<String, Object[]> result = new HashMap<>(names.size());
            for (String name : names) {
                Class<?> aClass = nameMap.get(name);
                if (aClass == null) {
                    aClass = Class.forName(name);
                    nameMap.put(name, aClass);
                }
                result.put(name, aClass.getEnumConstants());
            }
            return RestResponse.renderSuccess(result);
        } catch (ClassNotFoundException e) {
            return RestResponse.renderUserResourceError("不存在对应的字典信息");
        }
    }

    @GetMapping("name")
    @ApiOperation(value = "根据枚举名称查询枚举")
    @ResponseBody
    private RestResponse enumByName(@RequestParam("name") String name) {
        try {
            Class<?> aClass = nameMap.get(prefixPackage + name);
            if (aClass == null) {
                aClass = Class.forName(name);
                nameMap.put(name, aClass);
            }
            return RestResponse.renderSuccess(aClass.getEnumConstants());
        } catch (ClassNotFoundException e) {
            return RestResponse.renderUserResourceError("不存在对应的字典信息");
        }
    }
}
