package net.evecom.fastdev.boot.filter;

import net.evecom.fastdev.boot.handle.WebTransSecurityServer;
import net.evecom.fastdev.boot.utils.WebSecuritySerializeContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <P><B>脱敏拦截器，决定是否脱敏:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月16日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class WebTransSecurityInterceptor extends HandlerInterceptorAdapter {

    private WebTransSecurityServer webTransSecurityServer;

    public WebTransSecurityInterceptor(WebTransSecurityServer webTransSecurityServer) {
        this.webTransSecurityServer = webTransSecurityServer;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            if (webTransSecurityServer.isFuzzy(request)) {
                WebSecuritySerializeContext.fuzzy();
            }
            byte[] cryptKey = webTransSecurityServer.getCryptKey(request);
            if (cryptKey != null) {
                WebSecuritySerializeContext.setCryptoKey(cryptKey);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        WebSecuritySerializeContext.reset();
    }
}
