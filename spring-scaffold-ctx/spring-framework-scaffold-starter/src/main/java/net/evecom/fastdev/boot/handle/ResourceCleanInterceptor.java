/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.handle;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月13日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ResourceCleanInterceptor implements HandlerInterceptor {

    /**
     * 资源清空
     */
    private ResourceClean[] resourceCleans;

    public ResourceCleanInterceptor() {
    }

    public ResourceCleanInterceptor(List<ResourceClean> resourceCleans) {
        if (resourceCleans != null) {
            this.resourceCleans = resourceCleans.toArray(new ResourceClean[0]);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        for (ResourceClean resourceClean : resourceCleans) {
            resourceClean.clean();
        }
    }

    public boolean isEmpty() {
        return resourceCleans != null && resourceCleans.length == 0;
    }
}
