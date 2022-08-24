/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.mybatis.sqlparser;

import java.util.Collection;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年07月06日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@FunctionalInterface
public interface PermissionHandler {

    Collection<PermissionData> getPermissionData(String table);

    default boolean doTableFilter(String tableName) {
        return false;
    }
}
