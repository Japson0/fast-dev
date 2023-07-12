/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.elastic.model;

import net.evecom.elastic.enums.OrderType;

import java.io.Serializable;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年11月29日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class ESort implements Serializable {

    /**
     * 排序字段名称
     */
    private final String name;

    /**
     * 排序描述
     */
    private final OrderType orderType;

    private ESort(String name) {
        this(name, OrderType.DESC);
    }

    public ESort(String name, OrderType orderType) {
        this.name = name;
        this.orderType = orderType;
    }

    public String getName() {
        return name;
    }

    public OrderType getOrderType() {
        return orderType;
    }
}
