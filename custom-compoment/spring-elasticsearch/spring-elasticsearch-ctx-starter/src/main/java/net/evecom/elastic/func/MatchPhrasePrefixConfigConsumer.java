/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.elastic.func;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchPhrasePrefixQuery;
import net.evecom.elastic.annotations.MatchPhrasePrefixConfig;
import net.evecom.elastic.utils.EmptyUtils;

import java.util.function.Consumer;

/**
 * <P><B>MatchPhrasePrefixConfig:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年03月30日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class MatchPhrasePrefixConfigConsumer extends EOperateFunc<MatchPhrasePrefixQuery.Builder> {

    /**
     * MatchPhrasePrefixConfig
     */
    private final MatchPhrasePrefixConfig matchPhraseConfig;

    public MatchPhrasePrefixConfigConsumer(MatchPhrasePrefixConfig matchPhraseConfig) {
        this.matchPhraseConfig = matchPhraseConfig;
    }

    @Override
    public void init(MatchPhrasePrefixQuery.Builder queryBuilder) {
        if (!EmptyUtils.isNull(matchPhraseConfig.slop())) {
            queryBuilder.slop(matchPhraseConfig.slop());
        }
        if (!EmptyUtils.isNull(matchPhraseConfig.maxExpansions())) {
            queryBuilder.maxExpansions(matchPhraseConfig.maxExpansions());
        }
        if (!EmptyUtils.isNull(matchPhraseConfig.analyzer())) {
            queryBuilder.analyzer(matchPhraseConfig.analyzer());
        }
    }
}
