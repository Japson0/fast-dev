package net.evecom.elastic.func;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryVariant;
import co.elastic.clients.util.ObjectBuilder;

import java.util.function.Consumer;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月14日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public abstract class EOperateFunc<T extends ObjectBuilder<?>> implements Consumer<ObjectBuilder<? extends QueryVariant>> {


    @Override
    public void accept(ObjectBuilder<? extends QueryVariant> objectBuilder) {
        init((T)objectBuilder);
    }

    protected abstract void init(T t);
}
