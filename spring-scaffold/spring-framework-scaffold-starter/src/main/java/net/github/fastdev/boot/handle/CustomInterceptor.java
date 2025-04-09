package net.github.fastdev.boot.handle;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Collections;
import java.util.List;

/**
 * <P><B>自定义拦截器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2024年10月10日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface CustomInterceptor extends HandlerInterceptor {

    default List<String> excludePathPatterns() {return Collections.emptyList();}

    default public int order() {return Ordered.LOWEST_PRECEDENCE;}
}
