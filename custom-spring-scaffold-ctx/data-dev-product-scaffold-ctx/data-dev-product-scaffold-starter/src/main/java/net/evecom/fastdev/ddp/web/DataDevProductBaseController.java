/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.fastdev.ddp.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import net.evecom.fastdev.boot.template.BaseController;
import net.evecom.fastdev.boot.template.BaseService;
import net.evecom.fastdev.common.model.RestResponse;
import net.evecom.fastdev.ddp.DataDevProductPageConditionQuery;
import net.evecom.fastdev.ddp.charts.ChartsResponse;
import net.evecom.fastdev.ddp.charts.table.GridData;
import net.evecom.fastdev.mybatis.PageConditionDTO;
import net.evecom.fastdev.mybatis.annotation.BaseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;
import java.util.Collection;

/**
 * 知识图谱平台基础开发工程
 *
 * @author Nick Lv
 * @created 2022/8/26 11:05
 */
public class DataDevProductBaseController<ID extends Serializable, T extends BaseEntity<ID>> extends BaseController<ID, T> {

    /**
     * 泛型类
     */
    private final Class<?> clazz;

    public DataDevProductBaseController(BaseService<ID, T> baseService, Class<?> clazz) {
        super(baseService);
        this.clazz = clazz;
    }

    /**
     * 分页查询
     *
     * @param conditionDTO
     * @param <P>
     * @return
     */
    @PostMapping("gridPage")
    @ApiOperation("分页查询并返回表头")
    public <P> RestResponse<ChartsResponse<GridData<IPage<T>>>> getPageForGrid(DataDevProductPageConditionQuery<P> conditionDTO) {
        return RestResponse.renderSuccess(ChartsResponse.tableResponse(baseService.getPage(conditionDTO), clazz));
    }

    /**
     * 全表查询并返回表头
     *
     * @param condition
     * @param <P>
     * @return
     */
    @ApiOperation("全表查询并返回表头")
    public <P> RestResponse<ChartsResponse<GridData<Collection<T>>>> getAllForGrid(T condition) {
        PageConditionDTO dto = new PageConditionDTO();
        dto.setCondition(condition);
        dto.noPageAndCount();
        return RestResponse.renderSuccess(ChartsResponse.tableResponse(baseService.getPage(dto).getRecords(), clazz));
    }
}



