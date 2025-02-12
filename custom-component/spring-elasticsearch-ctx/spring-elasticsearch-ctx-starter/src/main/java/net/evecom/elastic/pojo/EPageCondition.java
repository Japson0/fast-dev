/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.elastic.pojo;

import net.evecom.elastic.model.EPageRequest;

/**
 * <P><B>ES分页搜索器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年01月24日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class EPageCondition<T, R> extends EPageRequest<R> {

    /**
     * 查询条件
     */
    private T condition;


    public EObjectQueryWrapper<T> buildQueryWrapper() {
        return new EObjectQueryWrapper<>(condition);
    }

    public EPageRequest<R> request() {
        return this;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }
}
