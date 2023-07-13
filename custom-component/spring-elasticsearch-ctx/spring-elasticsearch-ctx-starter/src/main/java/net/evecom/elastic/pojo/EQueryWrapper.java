package net.evecom.elastic.pojo;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.search.Highlight;

/**
 * <P><B>ES查询类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月15日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public interface EQueryWrapper {

    /**
     * 构建查询条件
     * RevisionTrail:(Date/Author/Description)
     * 2023年06月15日
     *
     * @author Japson Huang
     */
    Query buildSearchSourceBuilder();

    /**
     * 高亮信息
     * RevisionTrail:(Date/Author/Description)
     * 2023年06月15日
     *
     * @author Japson Huang
     */
    Highlight getHighlightBuilder();

    /**
     * 查询的字段
     * RevisionTrail:(Date/Author/Description)
     * 年06月15日
     *
     * @author Japson Huang
     */
    String[] getColumn();

    /**
     * 查询的索引
     * RevisionTrail:(Date/Author/Description)
     * 2023年06月15日
     *
     * @author Japson Huang
     */
    String getIndex();

    /**
     * 是否别名
     * RevisionTrail:(Date/Author/Description)
     * 2023年06月15日
     *
     * @author Japson Huang
     */
    boolean isAlias();

}
