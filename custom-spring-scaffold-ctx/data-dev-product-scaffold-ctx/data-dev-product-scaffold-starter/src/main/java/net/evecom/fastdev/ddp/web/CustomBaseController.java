/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.fastdev.ddp.web;

import io.swagger.annotations.ApiOperation;
import net.evecom.fastdev.boot.template.BaseController;
import net.evecom.fastdev.common.model.RestResponse;
import net.evecom.fastdev.common.web.BaseService;
import net.evecom.fastdev.ddp.charts.ChartsResponse;
import net.evecom.fastdev.ddp.charts.table.GridData;
import net.evecom.fastdev.mybatis.PageConditionDTO;
import net.evecom.fastdev.mybatis.annotation.BaseEntity;
import net.evecom.fastdev.mybatis.annotation.PageConditionQuery;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Collection;

/**
 * 知识图谱平台基础开发工程
 *
 * @author Nick Lv
 * @created 2022/8/26 11:05
 */
public class CustomBaseController<T extends BaseEntity<Long>> extends BaseController<Long, T> {


    public CustomBaseController(BaseService<Long, T> baseService) {
        super(baseService);
    }

    /**
     * 分页查询
     *
     * @param conditionDTO
     * @return
     */
    public RestResponse<GridData<PageConditionQuery<? extends T>>> getPageForGrid(PageConditionQuery<?> conditionDTO, Class<?> clazz) {
        PageConditionQuery<? extends T> page = baseService.getPage(conditionDTO);
        if (ignoreSchema()) {
            return RestResponse.renderSuccess(new GridData<>(baseService.getPage(page), null));
        }
        return ChartsResponse.tableResponse(page, clazz);
    }

    /**
     * 全表查询并返回表头
     *
     * @param condition
     * @return
     */
    protected RestResponse<GridData<? extends Collection<? extends T>>> getAllForGrid(Object condition, Class<?> clazz) {
        PageConditionDTO dto = new PageConditionDTO();
        dto.setCondition(condition);
        dto.noPageAndCount();
        if (ignoreSchema()) {
            return RestResponse.renderSuccess(new GridData<>(baseService.getPage(dto).getpRecords(), null));
        }
        return ChartsResponse.tableResponse(baseService.getPage(dto).getpRecords(), clazz);
    }

    @DeleteMapping("batch")
    @ApiOperation("批量删除")
    public RestResponse deleteBatch(@RequestParam Long[] ids) {
        return RestResponse.renderSuccess(baseService.deleteById(Arrays.asList(ids))).setMessage("删除成功");
    }

    protected boolean ignoreSchema() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return false;
        }
        return requestAttributes.getRequest().getParameter("ignoreSchema") != null;
    }
}



