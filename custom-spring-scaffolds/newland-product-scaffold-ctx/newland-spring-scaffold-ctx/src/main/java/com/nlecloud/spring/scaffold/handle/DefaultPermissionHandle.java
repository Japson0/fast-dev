package com.nlecloud.spring.scaffold.handle;

import com.nlecloud.upms.api.permission.PermissionService;
import org.apache.dubbo.config.annotation.DubboReference;

import java.util.Collection;

/**
 * <P><B>默认权限处理器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年09月16日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class DefaultPermissionHandle implements PermissionHandle {

    @DubboReference(cache = "lfu")
    private PermissionService permissionService;

    @Override
    public boolean checkPermissions(Collection<String> roles, String apiName) {
        return permissionService.checkPermissions(roles, apiName)!=null;
    }
}
