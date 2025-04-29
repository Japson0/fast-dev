package net.github.fastdev.boot.handle;

import org.apache.commons.lang3.LocaleUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * <P><B>国际化解析:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月10日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class CustomAcceptHeaderLocaleResolver implements LocaleResolver {

    public CustomAcceptHeaderLocaleResolver() {
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {

        String header = request.getHeader("Accept-Language-v1");
        if (header != null) {
            return LocaleUtils.toLocale(header);
        }
        return Locale.SIMPLIFIED_CHINESE;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        throw new UnsupportedOperationException(
                "Cannot change HTTP Accept-Language header - use a different locale resolution strategy");
    }
}
