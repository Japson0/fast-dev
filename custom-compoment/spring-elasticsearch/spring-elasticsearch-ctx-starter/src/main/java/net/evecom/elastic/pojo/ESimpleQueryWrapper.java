package net.evecom.elastic.pojo;

import co.elastic.clients.elasticsearch.core.search.Highlight;

import java.util.ArrayList;
import java.util.List;

/**
 * <P><B>简单查询类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月15日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ESimpleQueryWrapper extends AbstractQueryWrapper {

    /**
     * 暂时的列
     */
    private String[] column;

    /**
     * 索引
     */
    private String index;

    /**
     * 别名
     */
    private boolean alias;

    /**
     * 查询条件
     */
    private List<ESearchValue> eSearchValues;

    public ESimpleQueryWrapper(String index, boolean alias) {
        this(index, alias, null, null);
    }

    public ESimpleQueryWrapper(String index, boolean alias, String[] column) {
        this(index, alias, column, new ArrayList<>());
    }

    public ESimpleQueryWrapper(String index, boolean alias, String[] column, List<ESearchValue> eSearchValues) {
        this.column = column;
        this.index = index;
        this.alias = alias;
        this.eSearchValues = eSearchValues;
    }

    public ESimpleQueryWrapper append(ESearchValue eSearchValue) {
        this.eSearchValues.add(eSearchValue);
        return this;
    }

    public ESimpleQueryWrapper appendAll(List<ESearchValue> eSearchValue) {
        this.eSearchValues.addAll(eSearchValue);
        return this;
    }


    @Override
    protected List<ESearchValue> getSearchValue() {
        return this.eSearchValues;
    }

    @Override
    public Highlight getHighlightBuilder() {
        return null;
    }

    @Override
    public String[] getColumn() {
        return this.column;
    }

    @Override
    public String getIndex() {
        return this.index;
    }

    @Override
    public boolean isAlias() {
        return this.alias;
    }
}
