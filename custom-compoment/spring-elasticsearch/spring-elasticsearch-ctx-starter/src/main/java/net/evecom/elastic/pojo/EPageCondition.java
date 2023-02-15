/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.elastic.pojo;

/**
 * <P><B>ES分页搜索器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年01月24日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class EPageCondition<T, R extends EsBaseEntity> extends EPageRequest<R> {

    /**
     * 查询条件
     */
    private T condition;

    public EsQueryWrapper<T> buildQueryWrapper(Class<?> group) {
        return new EsQueryWrapper<>(condition, group);
    }

    public EsQueryWrapper<T> buildQueryWrapper() {
        return new EsQueryWrapper<>(condition);
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
