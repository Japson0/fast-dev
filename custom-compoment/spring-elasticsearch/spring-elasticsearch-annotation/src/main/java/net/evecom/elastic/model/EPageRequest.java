/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.elastic.model;

import net.evecom.elastic.enums.TotalHitRelation;

import java.io.Serializable;
import java.util.List;

/**
 * <P><B>ElasticSearch分页请求:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年11月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class EPageRequest<R> implements Serializable {

    /**
     * 当前页
     */
    private int page;


    /**
     * 一页的大小
     */
    private int size = 10;
    /**
     * 是否查询总数
     */
    private boolean searchCount = false;

    /**
     * 总数
     */
    private long total;

    /**
     * 总数比较值
     */
    private TotalHitRelation totalRel = TotalHitRelation.EQ;
    /**
     * 排序组
     */
    private ESort[] eSorts;

    /**
     * 返回值
     */
    private List<R> records;

    public EPageRequest() {
    }

    public EPageRequest(int page, int size) {
        this(page, size, true);
    }

    public EPageRequest(int page, int size, boolean searchCount, ESort... eSorts) {
        this.page = page;
        this.size = size;
        this.searchCount = searchCount;
        this.eSorts = eSorts;
    }

    public int getOffset() {
        if (page > 0) {
            return (page - 1) * size;
        }
        return page * size;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public List<R> getRecords() {
        return records;
    }

    public void setRecords(List<R> records) {
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public TotalHitRelation getTotalRel() {
        return totalRel;
    }

    public void setTotalRel(TotalHitRelation totalRel) {
        this.totalRel = totalRel;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ESort[] getESorts() {
        return eSorts;
    }

    public void setESorts(ESort[] eSorts) {
        this.eSorts = eSorts;
    }
}
