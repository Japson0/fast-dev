/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.template;

import io.swagger.annotations.ApiOperation;
import net.evecom.fastdev.common.model.RestResponse;
import net.evecom.fastdev.mybatis.annotation.BaseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * <P><B>公用控制层feign:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年01月04日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface BaseControllerInterface<ID extends Serializable, T extends BaseEntity<ID>> {

    /**
     * 更新操作，全覆盖
     * RevisionTrail:(Date/Author/Description)
     * 2021年07月26日
     *
     * @author Japson Huang
     */
    @PutMapping
    @ApiOperation("更新")
    RestResponse update(@RequestBody T entity);

    /**
     * 新增操作
     * RevisionTrail:(Date/Author/Description)
     * 2021年07月26日
     *
     * @author Japson Huang
     */
    @PostMapping
    @ApiOperation("新增")
    RestResponse insert(@RequestBody T entity);

    /**
     * 删除操作
     * RevisionTrail:(Date/Author/Description)
     * 2021年07月26日
     *
     * @author Japson Huang
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    RestResponse delete(@PathVariable ID id);

    /**
     * 查询明细
     */
    @GetMapping("{id}")
    @ApiOperation("查询")
    RestResponse<T> get(@PathVariable ID id);
}
