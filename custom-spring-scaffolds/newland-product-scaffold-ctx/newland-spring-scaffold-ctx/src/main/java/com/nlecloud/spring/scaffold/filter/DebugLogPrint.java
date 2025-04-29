package com.nlecloud.spring.scaffold.filter;

import net.github.fastdev.boot.handle.CustomInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <P><B>debug日志打印:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class DebugLogPrint implements CustomInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return CustomInterceptor.super.preHandle(request, response, handler);
    }
}
