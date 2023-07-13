/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.elastic.func;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import net.evecom.elastic.annotations.MatchConfig;
import net.evecom.elastic.enums.Operator;
import net.evecom.elastic.enums.ZeroTermsQuery;
import net.evecom.elastic.utils.EmptyUtils;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年03月30日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class MatchConfigConsumer extends EOperateFunc<MatchQuery.Builder> {

    /**
     * matchConfig
     */
    private final MatchConfig matchConfig;

    public MatchConfigConsumer(MatchConfig matchConfig) {
        this.matchConfig = matchConfig;
    }

    @Override
    public void init(MatchQuery.Builder query) {

        query.operator(matchConfig.operator() == Operator.AND ? co.elastic.clients.elasticsearch._types.query_dsl.Operator.And
                : co.elastic.clients.elasticsearch._types.query_dsl.Operator.Or);
        if (!EmptyUtils.isNull(matchConfig.prefixLength())) {
            query.prefixLength(matchConfig.prefixLength());
        }
        if (!EmptyUtils.isNull(matchConfig.maxExpansions())) {
            query.maxExpansions(matchConfig.maxExpansions());
        }
        query.fuzzyTranspositions(matchConfig.fuzzyTranspositions());
        query.lenient(matchConfig.lenient());

        if (matchConfig.zeroTermsQuery() == ZeroTermsQuery.ALL) {
            query.zeroTermsQuery(co.elastic.clients.elasticsearch._types.query_dsl.ZeroTermsQuery.All);
        }
        query.autoGenerateSynonymsPhraseQuery(matchConfig.autoGenerateSynonymsPhraseQuery());
        if (!EmptyUtils.isNull(matchConfig.analyzer())) {
            query.analyzer(matchConfig.analyzer());
        }
        if (!EmptyUtils.isNull(matchConfig.minimumShouldMatch())) {
            query.minimumShouldMatch(matchConfig.minimumShouldMatch());
        }
        if (!EmptyUtils.isNull(matchConfig.fuzzyRewrite())) {
            query.fuzzyRewrite(matchConfig.fuzzyRewrite());
        }
        if (!matchConfig.fuzziness().equals("NONE")) {
            query.fuzziness(matchConfig.fuzziness());
        }
    }
}
