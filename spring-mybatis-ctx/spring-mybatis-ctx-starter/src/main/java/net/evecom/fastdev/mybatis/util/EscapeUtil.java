/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.mybatis.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * 转义工具类
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月08日
 *
 * @author Japson Huang
 */
public class EscapeUtil {

    /**
     * 转义
     * RevisionTrail:(Date/Author/Description)
     * 2021年11月08日
     *
     * @author Japson Huang
     */
    public static String escapeChar(String before) {
        if (StringUtils.isNotBlank(before)) {
            before = before.replaceAll("\\\\", "\\\\\\\\");
            before = before.replaceAll("_", "\\\\_");
            before = before.replaceAll("%", "\\\\%");
        }
        return before;
    }


}
