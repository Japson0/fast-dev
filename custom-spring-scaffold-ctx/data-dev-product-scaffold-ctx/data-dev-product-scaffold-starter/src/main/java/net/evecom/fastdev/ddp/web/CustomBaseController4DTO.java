/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.fastdev.ddp.web;

import io.swagger.annotations.ApiOperation;
import net.evecom.fastdev.boot.template.BaseController4DTO;
import net.evecom.fastdev.common.model.RestResponse;
import net.evecom.fastdev.common.web.BaseService4DTO;
import net.evecom.fastdev.mybatis.annotation.BaseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

/**
 * 知识图谱平台基础开发工程
 *
 * @author Nick Lv
 * @created 2022/8/26 11:05
 */
public class CustomBaseController4DTO<T extends BaseEntity<Long>, DTO extends T> extends BaseController4DTO<Long, T, DTO> {


    public CustomBaseController4DTO(BaseService4DTO<Long, T, DTO> baseService) {
        super(baseService);
    }



    @DeleteMapping("batch")
    @ApiOperation("批量删除")
    public RestResponse deleteBatch(@RequestParam Long[] ids) {
        return RestResponse.renderSuccess(baseService.deleteById(Arrays.asList(ids))).setMessage("删除成功");
    }
}



