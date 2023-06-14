/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.elastic.pojo;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.util.ObjectBuilder;
import net.evecom.elastic.enums.ElasticBoolType;
import net.evecom.elastic.enums.ElasticOperator;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

/**
 * <P><B>搜索值:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年11月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ESearchValue implements Serializable {

    /**
     * 序列化号
     */
    private static final long serialVersionUID = -1910642529275144840L;
    /**
     * 搜索值
     */
    private final Object value;

    /**
     * 顺序
     */
    private int order;
    /**
     * 操作类型
     */
    private final ElasticOperator elasticOperator;

    /**
     * 查询类型
     */
    private final ElasticBoolType elasticBoolType;

    /**
     * queryBuilder
     */
    private final ObjectBuilder<? extends QueryVariant> objectBuilder;
    /**
     * 查询字段名称
     */
    private final String name;


    public ESearchValue(String name, Object value, ElasticOperator elasticOperator,
                        int order, ElasticBoolType elasticBoolType) {
        this(name, value, elasticOperator, order, elasticBoolType, 1);
    }

    public ESearchValue(String name, Object value, ElasticOperator elasticOperator,
                        int order, ElasticBoolType elasticBoolType, float boost) {
        Assert.notNull(value, "value 不允许为空");
        this.order = order;
        this.elasticBoolType = elasticBoolType;
        this.elasticOperator = elasticOperator;
        this.value = value instanceof Date ? ((Date) value).getTime() : value;
        this.objectBuilder = operator(elasticOperator, name, this.value, boost);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public ElasticOperator getElasticOperator() {
        return elasticOperator;
    }

    public int getOrder() {
        return order;
    }

    public ESearchValue setOrder(int order) {
        this.order = order;
        return this;
    }

    public ElasticBoolType getElasticBoolType() {
        return elasticBoolType;
    }


    public ObjectBuilder<? extends QueryVariant> objectBuilder() {
        return objectBuilder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ESearchValue that = (ESearchValue) o;
        return Objects.equals(this.getName(), that.getName()) && Objects.equals(value, that.value) && elasticOperator == that.elasticOperator && elasticBoolType == that.elasticBoolType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, elasticOperator, elasticBoolType);
    }

    private ObjectBuilder<? extends QueryVariant> operator(ElasticOperator operator, String field, Object value, float boost) {
        switch (operator) {
            case TERM: {
                return new TermQuery.Builder().field(field).value(fieldValue(value)).boost(boost);
            }
            case PREFIX: {
                return new PrefixQuery.Builder().field(field).value(value.toString()).boost(boost);
            }
            case TERMS: {
                List<FieldValue> fieldValues = new ArrayList<>();
                if (value instanceof Collection) {
                    ((Collection<?>) value).forEach(f -> fieldValues.add(fieldValue(f)));
                } else if (value instanceof Object[]) {
                    for (Object obj : (Object[]) value) {
                        fieldValues.add(fieldValue(obj));
                    }
                } else {
                    throw new IllegalArgumentException("[terms] vaue must List.class or Object[]");
                }
                return new TermsQuery.Builder().field(field).terms(
                        new TermsQueryField.Builder().value(fieldValues).build()).boost(boost);
            }
            case MATCH: {
                return new MatchQuery.Builder().field(field).query(fieldValue(value)).boost(boost);
            }
            case MATCH_PHRASE: {
                return new MatchPhraseQuery.Builder().field(field).query(value.toString()).boost(boost);
            }
            case MATCH_PHRASE_PREFIX: {
                return new MatchPhrasePrefixQuery.Builder().field(field).field(value.toString()).boost(boost);
            }
            case EXISTS: {
                return new ExistsQuery.Builder().field(field).boost(boost);
            }
            case GT: {
                return new RangeQuery.Builder().field(field).gt(JsonData.of(value)).boost(boost);
            }
            case GTE: {
                return new RangeQuery.Builder().field(field).gte(JsonData.of(value)).boost(boost);
            }
            case LT: {
                return new RangeQuery.Builder().field(field).lt(JsonData.of(value)).boost(boost);
            }
            case LTE: {
                return new RangeQuery.Builder().field(field).lte(JsonData.of(value)).boost(boost);
            }
            default:
                throw new UnsupportedOperationException("不支持的操作符类型");
        }
    }

    private FieldValue fieldValue(Object value) {
        if (value instanceof Number) {
            Class<?> aClass = value.getClass();
            if (aClass == Float.class || aClass == Double.class) {
                return FieldValue.of((((Number) value).doubleValue()));
            } else {
                return FieldValue.of(((Number) value).longValue());
            }
        } else if (value.getClass() == String.class) {
            return FieldValue.of(value.toString());
        } else if (value.getClass() == Boolean.class) {
            return FieldValue.of((Boolean) value);
        } else {
            throw new IllegalArgumentException("不支持的ES查询参数类型");
        }
    }
}
