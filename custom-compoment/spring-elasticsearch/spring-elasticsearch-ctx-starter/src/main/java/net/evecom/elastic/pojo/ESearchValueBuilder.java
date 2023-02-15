/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.elastic.pojo;

import net.evecom.elastic.enums.ElasticBoolType;
import net.evecom.elastic.enums.ElasticOperator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <P><B>搜索器构建器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年01月27日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ESearchValueBuilder {

    /**
     * 搜索列表
     */
    private List<ESearchValue> searchValues;

    public ESearchValueBuilder() {
        this.searchValues = new ArrayList<>();
    }

    public ESearchValueBuilder(int size) {
        this.searchValues = new ArrayList<>(size);
    }

    public ESearchValue term(String column, Object value) {
        return term(column, value, 99, ElasticBoolType.MUST);
    }

    public ESearchValue term(String column, Object value, int order, ElasticBoolType elasticBoolType) {
        ESearchValue eSearchValue = new ESearchValue(column, value, ElasticOperator.TERM, order, elasticBoolType);
        this.searchValues.add(eSearchValue);
        return eSearchValue;
    }

    public ESearchValue terms(String column, List<?> value) {
        return terms(column, value, 99, ElasticBoolType.MUST);
    }

    public ESearchValue terms(String column, List<?> value, int order, ElasticBoolType elasticBoolType) {
        ESearchValue eSearchValue = new ESearchValue(column, value, ElasticOperator.TERMS, order, elasticBoolType);
        this.searchValues.add(eSearchValue);
        return eSearchValue;
    }

    public ESearchValue match(String column, Object value) {
        return match(column, value, 99, ElasticBoolType.MUST);
    }

    public ESearchValue match(String column, Object value, int order, ElasticBoolType elasticBoolType) {
        ESearchValue eSearchValue = new ESearchValue(column, value, ElasticOperator.MATCH, order, elasticBoolType);
        this.searchValues.add(eSearchValue);
        return eSearchValue;
    }

    public ESearchValue matchPhrase(String column, Object value) {
        return matchPhrase(column, value, 99, ElasticBoolType.MUST);
    }

    public ESearchValue matchPhrase(String column, Object value, int order, ElasticBoolType elasticBoolType) {
        ESearchValue eSearchValue = new ESearchValue(column, value, ElasticOperator.MATCH_PHRASE, order, elasticBoolType);
        this.searchValues.add(eSearchValue);
        return eSearchValue;
    }

    public ESearchValue matchPhrasePrefix(String column, Object value) {
        return matchPhrasePrefix(column, value, 99, ElasticBoolType.MUST);
    }

    public ESearchValue matchPhrasePrefix(String column, Object value, int order, ElasticBoolType elasticBoolType) {
        ESearchValue eSearchValue = new ESearchValue(column, value, ElasticOperator.MATCH_PHRASE_PREFIX, order, elasticBoolType);
        this.searchValues.add(eSearchValue);
        return eSearchValue;
    }

    public ESearchValue prefix(String column, Object value) {
        return prefix(column, value, 99, ElasticBoolType.MUST);
    }

    public ESearchValue prefix(String column, Object value, int order, ElasticBoolType elasticBoolType) {
        ESearchValue eSearchValue = new ESearchValue(column, value, ElasticOperator.PREFIX, order, elasticBoolType);
        this.searchValues.add(eSearchValue);
        return eSearchValue;
    }

    public ESearchValue gt(String column, Object value, boolean equal) {
        return gt(column, value, 99, ElasticBoolType.MUST, equal);
    }

    public ESearchValue gt(String column, Object value, int order, ElasticBoolType elasticBoolType, boolean equal) {
        ESearchValue eSearchValue = new ESearchValue(column, value, equal ? ElasticOperator.GTE : ElasticOperator.GT, order, elasticBoolType);
        this.searchValues.add(eSearchValue);
        return eSearchValue;
    }

    public ESearchValue lt(String column, Object value, boolean equal) {
        return lt(column, value, 99, ElasticBoolType.MUST, equal);
    }

    public ESearchValue lt(String column, Object value, int order, ElasticBoolType elasticBoolType, boolean equal) {
        ESearchValue eSearchValue = new ESearchValue(column, value, equal ? ElasticOperator.LTE : ElasticOperator.LT, order, elasticBoolType);
        this.searchValues.add(eSearchValue);
        return eSearchValue;
    }

    public ESearchValue exists(String column) {
        return exists(column, 99, ElasticBoolType.MUST);
    }

    public ESearchValue exists(String column, int order, ElasticBoolType elasticBoolType) {
        ESearchValue eSearchValue = new ESearchValue(column, null, ElasticOperator.EXISTS, order, elasticBoolType);
        this.searchValues.add(eSearchValue);
        return eSearchValue;
    }

    public ESearchValueBuilder then() {
        return this;
    }

    public Collection<ESearchValue> build() {
        return this.searchValues;
    }

}
