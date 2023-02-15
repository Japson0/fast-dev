/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.template;

import net.evecom.fastdev.common.web.BaseService;
import net.evecom.fastdev.mybatis.annotation.BaseEntity;

import java.io.Serializable;

/**
 * <P><B>公用控制层:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年12月03日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class BaseController<ID extends Serializable, T extends BaseEntity<ID>> extends BaseController4DTO<ID, T, T> {

    public BaseController(BaseService<ID, T> baseService) {
        super(baseService);
    }

}
