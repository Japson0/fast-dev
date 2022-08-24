/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.mybatis.query;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlUtils;
import net.evecom.fastdev.mybatis.util.EscapeUtil;

/**
 * <P><B>普通转义查询类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月08日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class EscapeQueryWrapper<T> extends QueryWrapper<T> {


    protected QueryWrapper<T> likeValue(boolean condition, SqlKeyword keyword, String column, Object val, SqlLike sqlLike) {
        return maybeDo(condition, () -> appendLikeSegments(column, keyword, val, sqlLike));
    }

    protected void appendLikeSegments(String column, SqlKeyword sqlKeyword, Object val, SqlLike sqlLike) {
        final String genParamName = Constants.WRAPPER_PARAM + paramNameSeq.incrementAndGet();
        final String paramStr = getParamAlias() + Constants.WRAPPER_PARAM_MIDDLE + genParamName;
        ISqlSegment[] iSqlSegments = new ISqlSegment[3];
        if (val != null && val.getClass() == String.class) {
            String strParam = val.toString();
            String regex = EscapeUtil.escapeChar(strParam);
            if (strParam.length() != regex.length()) {
                val = regex;
            }
        }
        iSqlSegments[0] = new StrSql(column);
        iSqlSegments[1] = sqlKeyword;
        iSqlSegments[2] = new StrSql(SqlScriptUtils.safeParam(paramStr));
        String likeParam = SqlUtils.concatLike(val, sqlLike);
        paramNameValuePairs.put(genParamName, likeParam);
        expression.add(iSqlSegments);
    }
}
