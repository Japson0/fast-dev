/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.fastdev.ddp;

import net.evecom.fastdev.mybatis.PageConditionDTO;
import net.evecom.fastdev.mybatis.annotation.OrderInfo;

import java.util.Map;

/**
 * KgapPageConditionQuery
 *
 * @author Nick Lv
 * @created 2022/8/26 15:24
 */
public class DataDevProductPageConditionQuery<P> extends PageConditionDTO<P> {

    /**
     * 兼容旧版本的排序规则
     *
     * @param orderParams
     */
    public void setOrderParams(Map<String, String> orderParams) {
        for (Map.Entry<String, String> entry : orderParams.entrySet()) {
            this.addOrdersLast(ASC.equals(entry.getValue().toUpperCase()) ?
                    OrderInfo.asc(entry.getKey()) :
                    OrderInfo.desc(entry.getKey()));
        }
    }
}



