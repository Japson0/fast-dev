/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.elastic.pojo;

/**
 * <P><B>es基础类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年01月19日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface EsBaseEntity {

    /**
     * 获取ID
     * RevisionTrail:(Date/Author/Description)
     * 2022年01月19日
     *
     * @author Japson Huang
     */
    String getId();

    /**
     * 赋值ID
     * RevisionTrail:(Date/Author/Description)
     * 2022年01月19日
     *
     * @author Japson Huang
     */
    void setId(String id);
}
