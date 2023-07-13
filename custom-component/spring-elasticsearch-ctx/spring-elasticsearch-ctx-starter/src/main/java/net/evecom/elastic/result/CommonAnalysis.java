package net.evecom.elastic.result;

import co.elastic.clients.elasticsearch.core.search.Hit;
import net.evecom.elastic.model.EsBaseEntity;

/**
 * <P><B>普通分析类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月14日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class CommonAnalysis<R> implements ResultAnalysis<R> {

    /**
     * 是否基础ES类
     */
    private boolean isBase;

    public CommonAnalysis(ResultType resultType) {
        isBase=resultType==ResultType.ENTITY;
    }

    @Override
    public R apply(R r, Hit<R> hit) {

        if(isBase){
            ((EsBaseEntity)r).setId(hit.id());
        }
        return r;
    }
}
