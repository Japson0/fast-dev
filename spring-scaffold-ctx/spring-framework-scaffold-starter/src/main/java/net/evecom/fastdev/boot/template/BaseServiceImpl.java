/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.template;

import net.evecom.fastdev.common.web.BaseService;
import net.evecom.fastdev.mybatis.annotation.BaseEntity;
import net.evecom.fastdev.mybatis.injector.BaseMapperExtend;

import java.io.Serializable;

/**
 * <P><B>基本逻辑服务层:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年12月03日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class BaseServiceImpl<ID extends Serializable, R extends BaseEntity<ID>> extends BaseService4DTOImpl<ID, R, R> implements BaseService<ID, R> {


    public BaseServiceImpl(BaseMapperExtend<R> baseMapper) {
        super(baseMapper);
    }
}
