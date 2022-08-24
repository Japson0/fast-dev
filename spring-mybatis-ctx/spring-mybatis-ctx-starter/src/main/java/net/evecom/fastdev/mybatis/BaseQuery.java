/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.evecom.fastdev.mybatis.annotation.ConditionOperation;
import net.evecom.fastdev.mybatis.annotation.QueryClass;
import net.evecom.fastdev.mybatis.query.EscapeQueryWrapper;
import net.evecom.fastdev.mybatis.query.OracleQueryWrapper;
import net.evecom.fastdev.mybatis.query.QueryInspection;

import java.util.Collection;
import java.util.Set;

/**
 * <P><B>基础查询类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年09月11日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class BaseQuery<T> {

    /**
     * dbType;
     */
    private static boolean oracle;


    public QueryWrapper<T> buildQueryWrapper() {
        return buildQueryWrapper(this);
    }

    /**
     * 生成mybatis-plus的QueryWrapper
     * RevisionTrail:(Date/Author/Description)
     * 2021年09月09日
     *
     * @author Japson Huang
     */
    public static <T> QueryWrapper<T> buildQueryWrapper(Object object) {
        return buildQueryWrapper(object, false);
    }

    /**
     * 生成mybatis-plus的QueryWrapper，如果ignoreAlias为true，则忽略别名的判断
     * RevisionTrail:(Date/Author/Description)
     * 2021年09月09日
     *
     * @author Japson Huang
     * @
     */
    public static <T> QueryWrapper<T> buildQueryWrapper(Object object, boolean ignoreAlias) {
        if (object == null) return null;
        Class<?> conditionClass = object.getClass();
        QueryClass queryClass = conditionClass.getAnnotation(QueryClass.class);
        QueryWrapper<T> queryWrapper;
        if (queryClass != null && queryClass.isOracle()) {
            queryWrapper = new OracleQueryWrapper<>();
        } else {
            queryWrapper = oracle ? new OracleQueryWrapper<>() : new EscapeQueryWrapper<>();
        }
        return buildWrapper(object, queryWrapper, ignoreAlias);
    }

    public static <T> QueryWrapper<T> buildWrapper(Object object, QueryWrapper<T> queryWrapper, boolean ignoreAlias) {
        if (object == null) return queryWrapper;
        QueryInspection generator = QueryInspection.generator(object.getClass());
        if (generator != null) {
            if (queryWrapper == null) {
                queryWrapper = new QueryWrapper<>();
            }
            Set<QueryInspection.QueryFieldWrapper> queryFields = generator.getQueryFields();
            for (QueryInspection.QueryFieldWrapper queryField : queryFields) {
                Object value = queryField.getValue(object);
                if (isEmpty(value)) continue;
                ConditionOperation condition = queryField.getCondition();
                initQuery(queryWrapper, condition, queryField.getColumnName(ignoreAlias), value);
            }
        }
        return queryWrapper;
    }


    public static <T> QueryWrapper<T> buildOracleQueryWrapper(Object object, boolean ignoreAlias) {
        if (object == null) return null;
        return buildWrapper(object, new OracleQueryWrapper<>(), ignoreAlias);
    }

    public static <T> QueryWrapper<T> buildEscapeQueryWrapper(Object object, boolean ignoreAlias) {
        if (object == null) return null;
        return buildWrapper(object, new EscapeQueryWrapper<>(), ignoreAlias);
    }

    protected static QueryWrapper initQuery(QueryWrapper queryWrapper, ConditionOperation sqlCondition, String column, final Object value) {
        switch (sqlCondition) {
            case EQ: {
                queryWrapper.eq(column, value);
                break;
            }
            case LIKE: {
                queryWrapper.like(column, value);
                break;
            }
            case LEFT_LIKE: {
                queryWrapper.likeLeft(column, value);
                break;
            }
            case RIGHT_LIKE: {
                queryWrapper.likeRight(column, value);
                break;
            }
            case NOT_EQ: {
                queryWrapper.ne(column, value);
                break;
            }
            case IN: {
                queryWrapper.in(column, getArray(value));
                break;
            }
            case NOT_IN: {
                queryWrapper.notIn(column, getArray(value));
                break;
            }
            case LTE: {
                queryWrapper.le(column, value);
                break;
            }
            case LT: {
                queryWrapper.lt(column, value);
                break;
            }
            case GTE: {
                queryWrapper.ge(column, value);
                break;
            }
            case GT: {
                queryWrapper.gt(column, value);
                break;
            }
        }
        return queryWrapper;
    }

    protected static Object[] getArray(Object value) {
        if (value instanceof Collection) {
            return ((Collection<?>) value).toArray();
        } else if (value.getClass().isArray()) {
            return (Object[]) value;
        } else {
            return new Object[]{value};
        }
    }

    protected static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            if ("".equals(object)) {
                return true;
            }
        }
        if (object instanceof Collection) {
            return ((Collection) object).size() == 0;
        }
        return false;
    }

    static void initDbType(DbType dbType) {
        oracle = dbType == DbType.ORACLE || dbType == DbType.ORACLE_12C;
    }
}
