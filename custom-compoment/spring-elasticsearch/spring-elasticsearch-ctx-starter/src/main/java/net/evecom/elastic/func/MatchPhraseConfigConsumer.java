/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.elastic.func;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchPhrasePrefixQuery;
import net.evecom.elastic.annotations.MatchPhraseConfig;
import net.evecom.elastic.enums.ZeroTermsQuery;
import net.evecom.elastic.utils.EmptyUtils;

import java.util.function.Consumer;

/**
 * <P><B>MatchPhraseConfig:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年03月30日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class MatchPhraseConfigConsumer extends EOperateFunc<MatchPhrasePrefixQuery.Builder> {

    /**
     * MatchPhraseConfig
     */
    private final MatchPhraseConfig matchPhraseConfig;

    public MatchPhraseConfigConsumer(MatchPhraseConfig matchPhraseConfig) {
        this.matchPhraseConfig = matchPhraseConfig;
    }

    @Override
    public void init(MatchPhrasePrefixQuery.Builder queryBuilder) {
        if (!EmptyUtils.isNull(matchPhraseConfig.slop())) {
            queryBuilder.slop(matchPhraseConfig.slop());
        }
        if (matchPhraseConfig.zeroTermsQuery() == ZeroTermsQuery.ALL) {
            queryBuilder.zeroTermsQuery(co.elastic.clients.elasticsearch._types.query_dsl.ZeroTermsQuery.All);
        }
        if (!EmptyUtils.isNull(matchPhraseConfig.analyzer())) {
            queryBuilder.analyzer(matchPhraseConfig.analyzer());
        }
    }
}
