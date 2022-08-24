/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.mybatis.query;

import net.evecom.fastdev.mybatis.annotation.ConditionOperation;
import net.evecom.fastdev.mybatis.annotation.QueryClass;
import net.evecom.fastdev.mybatis.annotation.QueryField;
import net.evecom.fastdev.mybatis.util.CamelCaseUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <P><B>查询类解析器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年01月25日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class QueryInspection {

    /**
     * 缓存
     */
    private static final Map<Class<?>, QueryInspection> CACHE_INSPECT = new ConcurrentHashMap<>();


    /**
     * 查询类
     */
    private QueryClass queryClass;

    /**
     * 查询属性
     */
    private Set<QueryFieldWrapper> queryFields;

    private QueryInspection(Class<?> clazz) {
        init(clazz);
    }

    public static QueryInspection generator(Class<?> clazz) {
        if (clazz == Object.class || clazz == null) return null;
        QueryInspection queryInspection = CACHE_INSPECT.get(clazz);
        if (queryInspection == null) {
            queryInspection = new QueryInspection(clazz);
            CACHE_INSPECT.put(clazz, queryInspection);
        }
        return queryInspection;
    }

    private static Method getReadMethod(Field field, Class<?> clazz) {
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), clazz);
            return propertyDescriptor.getReadMethod();
        } catch (IntrospectionException e) {
            return null;
        }
    }

    private void init(Class<?> clazz) {
        this.queryClass = clazz.getAnnotation(QueryClass.class);
        Field[] fields = clazz.getDeclaredFields();
        Set<QueryFieldWrapper> tempQueryFields = new HashSet<>(fields.length);
        for (Field field : fields) {
            Method readMethod = getReadMethod(field, clazz);
            if (readMethod == null) continue;
            QueryField queryField = field.getAnnotation(QueryField.class);
            if (queryField == null) {
                tempQueryFields.add(new QueryFieldWrapper(CamelCaseUtils.toUnderlineName(field.getName()), readMethod));
            } else if (queryField.exists()) {
                if (queryField.value().equals("")) {
                    tempQueryFields.add(new QueryFieldWrapper(queryField, CamelCaseUtils.toUnderlineName(field.getName()), readMethod));
                } else {
                    tempQueryFields.add(new QueryFieldWrapper(queryField, null, readMethod));
                }
            }
        }
        QueryInspection parent = generator(clazz.getSuperclass());
        if (parent != null) {
            tempQueryFields.addAll(parent.queryFields);
        }
        this.queryFields = Collections.unmodifiableSet(tempQueryFields);
    }

    public Set<QueryFieldWrapper> getQueryFields() {
        return queryFields;
    }


    final public class QueryFieldWrapper {

        /**
         * 读方法
         */
        private final Method readMethod;
        /**
         * 数据库字段名，如果为空则取参数名的驼峰映射
         */
        private final String column;

        /**
         * 操作符，如LIKE、EQ之类的
         */
        private final ConditionOperation condition;

        /**
         * 字段别名，比如A.NAME
         */
        private final String alias;

        /**
         * 忽略主类的注解
         */
        private final boolean ignoreClassAlias;

        public QueryFieldWrapper(QueryField queryField, String defaultValue, Method readMethod) {
            this.column = defaultValue == null ? queryField.value() : defaultValue;
            this.condition = queryField.condition();
            this.alias = queryField.alias().equals("") ? null : queryField.alias();
            this.ignoreClassAlias = queryField.ignoreClassAlias();
            this.readMethod = readMethod;
        }

        public QueryFieldWrapper(String column, Method readMethod) {
            this.column = column;
            this.alias = null;
            this.condition = ConditionOperation.EQ;
            this.ignoreClassAlias = false;
            this.readMethod = readMethod;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            QueryFieldWrapper that = (QueryFieldWrapper) o;
            return Objects.equals(readMethod.getName(), that.readMethod.getName());
        }

        @Override
        public int hashCode() {

            return Objects.hash(readMethod.getName());
        }

        public String getColumnName(boolean ignoreAlias) {
            if (ignoreAlias) return column;
            if (queryClass == null) {
                return getAliasColumn();
            } else {
                if (this.ignoreClassAlias) {
                    return getAliasColumn();
                } else {
                    return !"".equals(queryClass.alias()) ? queryClass.alias() + "." + column : column;
                }
            }
        }

        private String getAliasColumn() {
            return alias != null ? alias + "." + column : column;
        }

        public ConditionOperation getCondition() {
            return condition;
        }

        public Object getValue(Object obj) {
            try {
                return readMethod.invoke(obj);
            } catch (Exception e) {
                return null;
            }
        }
    }
}
