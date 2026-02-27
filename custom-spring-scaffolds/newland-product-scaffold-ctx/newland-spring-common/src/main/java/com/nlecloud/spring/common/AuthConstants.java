package com.nlecloud.spring.common;

/**
 * <P><B>授权常量:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月16日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class AuthConstants {

    private AuthConstants() {}

    public static final String USER_HEADER = "x-user-header";
    public static final String USER_ID_HEADER = "x-userid-header";

    public static final String NICK_NAME_HEADER ="x-name-header";

    public static final String ROLE_HEADER = "x-role-header";

    public static final String SCHOOL_ID_HEADER = "x-school-header";


    public static final String TENANT_ID_HEADER = "v1-tenant-header";

    public static final String CURRENT_TENANT_ID_HEADER = "x-current-tenant-header";
}
