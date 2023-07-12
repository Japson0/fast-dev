/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.mybatis.query;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;

/**
 * <P><B>字符串SQL:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月08日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class StrSql implements ISqlSegment {
    /**
     * 字串
     */
    public final String str;

    public StrSql(String str) {
        this.str = str;
    }

    @Override
    public String getSqlSegment() {
        return str;
    }
}
