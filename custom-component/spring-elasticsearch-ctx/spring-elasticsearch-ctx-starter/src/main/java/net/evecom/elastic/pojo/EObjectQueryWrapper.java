/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.elastic.pojo;

import co.elastic.clients.elasticsearch.core.search.Highlight;
import net.evecom.elastic.annotations.ElasticQueryIndex;
import org.springframework.util.Assert;

import java.util.*;

/**
 * <P><B>内省类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年12月02日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class EObjectQueryWrapper<T> extends AbstractQueryWrapper {


    private static final Map<Class, EInspectation> CACHE_INSPECTATION = new HashMap<>();
    /**
     * ES搜索项
     */
    private Collection<ESearchValue> searchValues;
    /**
     * 返回的值
     */
    private String[] includes;
    /**
     * 实体类
     */
    private T searchObj;
    /**
     * ES实体类解析
     */
    private EInspectation<T> eInspectation;

    /**
     * 默认高亮
     */
    private boolean highLight=true;

    public EObjectQueryWrapper(T obj) {
        Assert.notNull(obj, "obj must not be mull!");
        init(obj.getClass());
        this.searchObj = obj;
        this.includes = eInspectation.getSource();
    }

    public EObjectQueryWrapper(Class<T> clazz) {
        init(clazz);
    }

    private void init(Class<?> clazz) {
        this.eInspectation = CACHE_INSPECTATION.get(clazz);
        if (eInspectation == null) {
            synchronized (this) {
                if (eInspectation == null) {
                    this.eInspectation = new EInspectation(clazz);
                    CACHE_INSPECTATION.put(clazz, eInspectation);
                }
            }
        }
    }


    private static int compareTo(Object o1, Object o2) {
        if (o1 instanceof Date) {
            return ((Date) o1).compareTo((Date) o2);
        } else if (o1 instanceof Long) {
            return ((Long) o1).compareTo((Long) o2);
        } else if (o1 instanceof String) {
            return ((String) o1).compareTo((String) o2);
        } else if (o1 instanceof Integer) {
            return ((Integer) o1).compareTo((Integer) o2);
        } else {
            throw new IllegalArgumentException
                    ("can't support tpye:[" + o1.getClass().getName() + "] in RangeQueryBuilder");
        }
    }


    @Override
    public String[] getColumn() {
        return getElasticClass().source();
    }

    @Override
    public String getIndex() {
        return getElasticClass().index();
    }

    @Override
    public boolean isAlias() {
        return getElasticClass().isAlias();
    }

    public T getSearchObj() {
        return searchObj;
    }

    public void clean() {
        this.searchValues = null;
        this.includes = null;
    }

    public ElasticQueryIndex getElasticClass() {
        return eInspectation.getElasticClass();
    }

    public boolean isHighLight() {
        return highLight;
    }

    public EObjectQueryWrapper<T> setHighLight(boolean highLight) {
        this.highLight = highLight;
        return this;
    }

    public String[] getSource() {
        return eInspectation.getSource();
    }

    public EObjectQueryWrapper<T> operator(ESearchValue eSearchValue) {
        this.searchValues.add(eSearchValue);
        return this;
    }

    public EObjectQueryWrapper<T> operator(Collection<ESearchValue> eSearchValue) {
        this.searchValues.addAll(eSearchValue);
        return this;
    }

    public Collection<ESearchValue> operator() {
        return this.searchValues;
    }


    private void checkNull() {
        if (searchValues == null) {
            searchValues = new ArrayList<>();
        }
    }

    public List<ESearchValue> getSearchValue() {
        int size = 0;
        if (searchValues != null) {
            size = searchValues.size();
        }
        List<ESearchValue> objSearchValue = null;
        if (this.searchObj != null) {
            objSearchValue = eInspectation.buildSearchValue(searchObj);
            size += objSearchValue.size();
        }
        if (size == 0) {
            return Collections.emptyList();
        }
        List<ESearchValue> result = new ArrayList<>(size);
        if (objSearchValue != null) {
            result.addAll(objSearchValue);
        }
        if (this.searchValues != null) {
            result.addAll(this.searchValues);
        }
        return result;
    }

    @Override
    public Highlight getHighlightBuilder() {
        return highLight ?eInspectation.highlightBuilder():null;
    }


}
