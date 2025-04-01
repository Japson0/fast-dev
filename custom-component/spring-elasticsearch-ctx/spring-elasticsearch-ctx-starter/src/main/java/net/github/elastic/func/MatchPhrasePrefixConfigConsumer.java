

package net.github.elastic.func;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchPhrasePrefixQuery;
import net.github.elastic.annotations.MatchPhrasePrefixConfig;
import net.github.elastic.utils.EmptyUtils;

/**
 * <P><B>MatchPhrasePrefixConfig:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年03月30日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
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
