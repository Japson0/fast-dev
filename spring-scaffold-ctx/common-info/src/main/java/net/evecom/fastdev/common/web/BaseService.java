/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.common.web;

import net.evecom.fastdev.mybatis.annotation.BaseEntity;

import java.io.Serializable;

/**
 * <P><B>单表传输层:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年12月03日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public interface BaseService<ID extends Serializable, R extends BaseEntity<ID>> extends BaseService4DTO<ID, R, R> {

}
