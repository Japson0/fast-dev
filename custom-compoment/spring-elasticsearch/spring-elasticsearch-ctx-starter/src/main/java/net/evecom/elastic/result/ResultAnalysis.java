package net.evecom.elastic.result;

import co.elastic.clients.elasticsearch.core.search.Hit;

import java.util.function.BiFunction;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月14日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface ResultAnalysis<R> extends BiFunction<R, Hit<R>, R> {
}
