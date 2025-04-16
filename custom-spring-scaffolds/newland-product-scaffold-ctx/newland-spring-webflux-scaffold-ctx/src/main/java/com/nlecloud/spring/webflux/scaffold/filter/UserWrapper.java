package com.nlecloud.spring.webflux.scaffold.filter;

import reactor.util.context.Context;
import reactor.util.context.ContextView;

import java.util.Set;

/**
 * <P><B>用户包装类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserWrapper extends com.necloud.spring.common.handle.UserWrapper {


    private static final String KEY_INFO = "USER_INFO_KEY";

    public UserWrapper(Long userId, String username, Set<String> roles) {
        super(userId, username, roles);
    }

    ContextView getContextView() {
        return Context.of(KEY_INFO,this);
    }


    public static UserWrapper getUserWrapper(ContextView contextView) {
        return contextView.get(KEY_INFO);
    }
}
