
package com.nlecloud.spring.scaffold.common;

/**
 * <P><B>基础表属性:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2024年09月11日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class TableColumn {


    private TableColumn() {
    }

    /**
     * 创建用户ID
     */
    public static final String CREATE_USER_ID="CREATE_PERSON";

    public static final String CREATE_USER_ID_NAME="createPerson";
    /**
     * 更新用户ID
     */
    public static final String UPDATE_USER_ID="UPDATE_PERSON";

    public static final String UPDATE_USER_ID_NAME="updatePerson";

    /**
     * 创建时间
     */
    public static final String CREATE_TIME="CREATE_TIME";

    public static final String CREATE_TIME_NAME="createTime";

    /**
     * 更新时间
     */
    public static final String UPDATE_TIME="UPDATE_TIME";

    public static final String UPDATE_TIME_NAME="updateTime";

}
