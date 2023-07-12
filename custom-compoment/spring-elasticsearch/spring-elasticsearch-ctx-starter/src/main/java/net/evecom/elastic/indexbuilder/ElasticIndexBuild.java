/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.elastic.indexbuilder;

/**
 * <P><B>增改删索引构建器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年01月19日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public interface ElasticIndexBuild<T> {

    String buildIndices(T obj, String alias, String[] indices);

    default boolean isAlias() {
        return false;
    }
}
