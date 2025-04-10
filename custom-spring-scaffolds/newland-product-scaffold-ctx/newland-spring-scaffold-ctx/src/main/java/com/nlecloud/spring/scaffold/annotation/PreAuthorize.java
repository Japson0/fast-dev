package com.nlecloud.spring.scaffold.annotation;

/**
 * <P><B>权限控制:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年03月27日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public @interface PreAuthorize {


    /**
     * 角色名称
     */
    String[] value();


}
