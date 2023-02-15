/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.fastdev.ddp.web;

import net.evecom.fastdev.common.web.BaseService;
import net.evecom.fastdev.mybatis.annotation.BaseEntity;

/**
 * 知识图谱平台基础开发工程
 *
 * @author Nick Lv
 * @created 2022/8/26 11:05
 */
public class CustomBaseController<T extends BaseEntity<Long>> extends CustomBaseController4DTO<T, T> {

    public CustomBaseController(BaseService<Long, T> baseService) {
        super(baseService);
    }
}



