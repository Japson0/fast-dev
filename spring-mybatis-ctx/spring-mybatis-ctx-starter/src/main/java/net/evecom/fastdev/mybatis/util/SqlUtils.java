/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.mybatis.util;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;

import java.util.Collection;
import java.util.List;

/**
 * <P><B>sql工具类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年10月08日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class SqlUtils {

    /**
     * 新增SQL
     */
    public final static String INSERT_SQL = "INSERT INTO %s %s VALUES %s";

    /**
     * 更新SQL
     */
    public final static String UPDATE_SQL = "UPDATE %s SET %s WHERE %s";

    /**
     * 选择SQL
     */
    public final static String SELECT_SQL = " SELECT %S FROM %s %s %s";

    /**
     * 是Select语句
     * RevisionTrail:(Date/Author/Description)
     * 2021年10月08日
     *
     * @author Japson Huang
     */
    public static boolean isSelect(String sql) {
        try {
            Statement parse = CCJSqlParserUtil.parse(sql);
            return parse instanceof Select;
        } catch (JSQLParserException e) {
            return false;
        }
    }

    /**
     * 是delete
     * RevisionTrail:(Date/Author/Description)
     * 2021年10月08日
     *
     * @author Japson Huang
     */
    public static boolean isDelete(String sql) {
        try {
            Statement parse = CCJSqlParserUtil.parse(sql);
            return parse instanceof Delete;
        } catch (JSQLParserException e) {
            return false;
        }
    }

    /**
     * 是否update语句
     * RevisionTrail:(Date/Author/Description)
     * 2021年10月08日
     *
     * @author Japson Huang
     */
    public static boolean isUpdate(String sql) {
        try {
            Statement parse = CCJSqlParserUtil.parse(sql);
            return parse instanceof Update;
        } catch (JSQLParserException e) {
            return false;
        }
    }

    /**
     * 是insert语句
     * RevisionTrail:(Date/Author/Description)
     * 2021年10月08日
     *
     * @author Japson Huang
     */
    public static boolean isInsert(String sql) {
        try {
            Statement parse = CCJSqlParserUtil.parse(sql);
            return parse instanceof Insert;
        } catch (JSQLParserException e) {
            return false;
        }
    }

    /**
     * 是DML语句   DML包含（Insert、Update、Delete）
     * RevisionTrail:(Date/Author/Description)
     * 2021年10月08日
     *
     * @author Japson Huang
     */
    public static boolean isDML(String sql) {
        try {
            Statement parse = CCJSqlParserUtil.parse(sql);
            return parse instanceof Insert || parse instanceof Update || parse instanceof Delete;
        } catch (JSQLParserException e) {
            return false;
        }
    }

    /**
     * 生成Inert语句
     * RevisionTrail:(Date/Author/Description)
     * 2021年09月02日
     *
     * @param table      表名
     * @param properties 字段名
     * @author Japson Huang
     */
    public static String buildInsertSQL(String table, List<String> properties) {
        StringBuilder columnValue = new StringBuilder(properties.size() * 5);
        StringBuilder placeHolder = new StringBuilder(properties.size() * 2 + 2);
        int pos = 0;
        columnValue.append("(");
        placeHolder.append("(");
        for (String property : properties) {
            columnValue.append(property);
            placeHolder.append("?");
            if (++pos != properties.size()) {
                columnValue.append(",");
                placeHolder.append(",");
            }
        }
        columnValue.append(")");
        placeHolder.append(")");
        return String.format(INSERT_SQL, table, columnValue, placeHolder);
    }

    /**
     * 生成updateSql 根据Id更新
     * RevisionTrail:(Date/Author/Description)
     * 2021年09月02日
     *
     * @param table      表名
     * @param properties 字段名
     * @param id         主键字段
     */
    public static String buildUpdateByISql(String table, List<String> properties, String id) {
        return String.format(UPDATE_SQL, table, buildBaseUpdateSql(properties), id + "=?");
    }

    /**
     * 生成updateSql,根据where条件更新
     *
     * @param table      表名
     * @param properties 字段名
     * @param where      判断条件字段
     */
    public static String buildUpdateSql(String table, List<String> properties, List<String> where) {

        if (where.size() == 1) {
            return buildUpdateByISql(table, properties, where.get(0));
        }
        return String.format(UPDATE_SQL, table, buildBaseUpdateSql(properties), buildWhere(where));
    }


    /**
     * 添加描述
     * RevisionTrail:(Date/Author/Description)
     * 2021年10月08日
     *
     * @param table 表明
     * @param ids   判断条件字段
     * @author Japson Huang
     */
    public static String buildSelectCountSql(String table, List<String> ids) {
        return String.format(SELECT_SQL, "COUNT(*)", table, "WHERE", buildWhere(ids));
    }

    public static String buildWhere(Collection<String> where) {
        int pos = 0;
        StringBuilder whereSql = new StringBuilder(where.size() * 8);
        for (String w : where) {
            whereSql.append(w).append("=?");
            if (++pos != where.size()) {
                whereSql.append(" AND ");
            }
        }
        return whereSql.toString();
    }

    private static String buildBaseUpdateSql(Collection<String> properties) {
        StringBuilder sql = new StringBuilder(properties.size() * 5);
        int pos = 0;
        for (String property : properties) {
            sql.append(property).append("=?");
            if (++pos != properties.size()) {
                sql.append(",");
            }
        }
        return sql.toString();
    }
}
