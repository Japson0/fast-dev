package com.nlecloud.spring.scaffold.handle;

import java.util.Collection;

/**
 * <P><B>权限处理器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年09月16日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface PermissionHandle  {


    boolean checkPermissions(Collection<String> roles,String apiName);

}
