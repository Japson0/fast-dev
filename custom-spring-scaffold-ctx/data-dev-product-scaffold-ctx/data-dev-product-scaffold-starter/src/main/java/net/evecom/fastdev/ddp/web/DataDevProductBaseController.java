/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.fastdev.ddp.web;

import io.swagger.annotations.ApiOperation;
import net.evecom.fastdev.boot.template.BaseController;
import net.evecom.fastdev.common.web.BaseService;
import net.evecom.fastdev.ddp.charts.ChartsResponse;
import net.evecom.fastdev.ddp.charts.table.GridData;
import net.evecom.fastdev.mybatis.PageConditionDTO;
import net.evecom.fastdev.mybatis.annotation.BaseEntity;
import net.evecom.fastdev.mybatis.annotation.PageConditionQuery;

import java.util.Collection;

/**
 * 知识图谱平台基础开发工程
 *
 * @author Nick Lv
 * @created 2022/8/26 11:05
 */
public class DataDevProductBaseController<T extends BaseEntity<Long>> extends BaseController<Long, T> {


    public DataDevProductBaseController(BaseService<Long, T> baseService) {
        super(baseService);
    }

    /**
     * 分页查询
     *
     * @param conditionDTO
     * @param <P>
     * @return
     */
    protected <P> ChartsResponse<GridData<PageConditionQuery<T>>> getPageForGrid(PageConditionQuery<P> conditionDTO, Class<?> clazz) {
        PageConditionQuery<T> page = baseService.getPage(conditionDTO);
        return ChartsResponse.tableResponse(page, clazz);
    }

    /**
     * 全表查询并返回表头
     *
     * @param condition
     * @return
     */
    @ApiOperation("全表查询并返回表头")
    protected ChartsResponse<GridData<Collection<T>>> getAllForGrid(T condition, Class<?> clazz) {
        PageConditionDTO dto = new PageConditionDTO();
        dto.setCondition(condition);
        dto.noPageAndCount();
        return ChartsResponse.tableResponse(baseService.getPage(dto).getpRecords(), clazz);
    }
}



