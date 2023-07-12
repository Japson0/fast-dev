/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.elastic.pojo;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryVariant;
import co.elastic.clients.elasticsearch.core.search.Highlight;
import co.elastic.clients.elasticsearch.core.search.HighlightField;
import co.elastic.clients.util.ObjectBuilder;
import net.evecom.elastic.annotations.*;
import net.evecom.elastic.enums.ElasticOperator;
import net.evecom.elastic.func.EOperateFunc;
import net.evecom.elastic.func.MatchConfigConsumer;
import net.evecom.elastic.func.MatchPhraseConfigConsumer;
import net.evecom.elastic.func.MatchPhrasePrefixConfigConsumer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <P><B>ES内省解析类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年01月18日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class EInspectation<T> {

    /**
     * 类型
     */
    private final Class<T> clazz;
    /**
     * 属性
     */
    private final List<EFieldProperty> properties;
    /**
     * 需要返回哪些值
     */
    private final String[] include;
    /**
     * 查询索引信息
     */
    private final ElasticQueryIndex elasticQueryIndex;
    /**
     * 高亮信息
     */
    private Highlight highlightBuilder;

    public String getIndex() {
        return elasticQueryIndex.index();
    }

    public String[] getSource() {
        return include;
    }

    public ElasticQueryIndex getElasticClass() {
        return this.elasticQueryIndex;
    }

    public EInspectation(Class<T> clazz) {
        this.elasticQueryIndex = clazz.getAnnotation(ElasticQueryIndex.class);
        if (elasticQueryIndex == null) {
            throw new IllegalArgumentException("该类不存在@ElasticQueryIndex 注解");
        }
        this.clazz = clazz;

        List<EFieldProperty> tempProperties = new ArrayList<>();
        Class currClass = clazz;
        Set<String> include = new HashSet<>(Arrays.asList(elasticQueryIndex.source()));
        while (currClass != Object.class) {
            for (Field declaredField : currClass.getDeclaredFields()) {
                ElasticQueryField annotation = declaredField.getAnnotation(ElasticQueryField.class);
                if (annotation != null) {
                    try {
                        tempProperties.add(new EFieldProperty(new PropertyDescriptor(declaredField.getName(), clazz).getReadMethod()
                                , annotation).init(declaredField));
                        if (elasticQueryIndex.includeField()) {
                            include.add(annotation.name());
                        }
                    } catch (IntrospectionException ignore) {
                    }
                }
            }
            currClass = currClass.getSuperclass();
        }
        this.properties = Collections.unmodifiableList(tempProperties);
        this.include = include.toArray(new String[0]);
    }

    public List<ESearchValue> buildSearchValue(T obj) {

        List<ESearchValue> searchValues = new ArrayList<>(properties.size());
        for (EFieldProperty eFieldProperty : properties) {
            Object invokeValue = eFieldProperty.getValue(obj);
            if (invokeValue == null) continue;
            ElasticQueryField queryField = eFieldProperty.elasticQueryField;
            ESearchValue eSearchValue = new ESearchValue(queryField.name(), invokeValue, queryField.operator()
                    , queryField.order(), queryField.boolType(), queryField.boost());
            EOperateFunc<? extends ObjectBuilder<?>> config = eFieldProperty.getConfig(queryField.operator());
            if (config != null) {
                ObjectBuilder<? extends QueryVariant> objectBuilder = eSearchValue.objectBuilder();
                config.accept(objectBuilder);
            }
            searchValues.add(eSearchValue);
        }
        return searchValues;
    }


    public Highlight highlightBuilder() {
        if (highlightBuilder == null) {
            synchronized (this) {
                if (highlightBuilder == null) {
                    Map<String, HighlightField> lightFieldSet = new HashMap<>();
                    for (EFieldProperty property : properties) {
                        if (property.higLight != null) {
                            HighlightField.Builder field = new HighlightField.Builder();
                            if (!"".equals(property.higLight.preTags())) {
                                field.preTags(property.higLight.preTags());
                            }
                            if (!"".equals(property.higLight.post_tags())) {
                                field.postTags(property.higLight.post_tags());
                            }
                            lightFieldSet.put(property.getElasticQueryField().name(), field.build());
                        }
                    }
                    if (lightFieldSet.isEmpty()) {
                        this.highlightBuilder = null;
                    } else {
                        Highlight.Builder builder = new Highlight.Builder();
                        highlightBuilder=builder.fields(lightFieldSet).build();
                    }
                }
            }
        }
        return highlightBuilder;
    }


    public class EFieldProperty {
        /**
         * 读方法
         */
        private final Method readMethod;

        /**
         * 属性
         */
        private final ElasticQueryField elasticQueryField;

        /**
         * 高亮属性
         */
        private HigLight higLight;

        /**
         * 配置列表
         */
        private  Map<ElasticOperator, EOperateFunc<? extends ObjectBuilder<?>>> configMap;


        public EFieldProperty(Method readMethod, ElasticQueryField elasticQueryField) {
            this.readMethod = readMethod;
            this.elasticQueryField = elasticQueryField;
        }

        public ElasticQueryField getElasticQueryField() {
            return elasticQueryField;
        }

        public HigLight getHigLight() {
            return higLight;
        }

        EFieldProperty init(Field field) {
            higLight = field.getAnnotation(HigLight.class);
            Annotation[] annotations = field.getAnnotations();
            Map<ElasticOperator, EOperateFunc<? extends ObjectBuilder<?>>> annotationMap = new HashMap<>();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> aClass = annotation.annotationType();
                if (aClass == HigLight.class) {
                    higLight = (HigLight) annotation;
                } else if (aClass == MatchConfig.class) {
                    annotationMap.put(ElasticOperator.MATCH, new MatchConfigConsumer((MatchConfig) annotation));
                } else if (aClass == MatchPhraseConfig.class) {
                    annotationMap.put(ElasticOperator.MATCH_PHRASE,
                            new MatchPhraseConfigConsumer((MatchPhraseConfig) annotation));
                } else if (aClass == MatchPhrasePrefixConfig.class) {
                    annotationMap.put(ElasticOperator.MATCH_PHRASE_PREFIX,
                            new MatchPhrasePrefixConfigConsumer((MatchPhrasePrefixConfig) annotation));
                }
            }
            if (!annotationMap.isEmpty()) {
                configMap = annotationMap;
            }
            return this;
        }

        public Object getValue(Object object) {
            try {
                return readMethod.invoke(object);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
        public EOperateFunc<? extends ObjectBuilder<?>> getConfig(ElasticOperator elasticOperator) {
            return configMap != null ? configMap.get(elasticOperator) : null;
        }
    }

}
