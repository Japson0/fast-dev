package com.nlecloud.spring.scaffold.handle;

import com.nlecloud.spring.annotation.UserInfo;
import org.slf4j.MDC;

/**
 *
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月14日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserMDCHandle {

    private static final String USER_ID = "user_id";

    private static final String TENANT_ID = "tenant_id";

    public static void initUserMDC(UserInfo userInfo) {
        if(userInfo!=null){
            if (userInfo.getUserId() != null) {
                MDC.put(USER_ID, userInfo.getUserId().toString());
            }
            if (userInfo.getTenantId() != null) {
                MDC.put(TENANT_ID, userInfo.getTenantId().toString());
            }
        }

    }

    public static void remove() {
        MDC.remove(USER_ID);
        MDC.remove(TENANT_ID);
    }
}
