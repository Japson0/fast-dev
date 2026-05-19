package com.nlecloud.spring.webflux.scaffold;

import org.apache.commons.lang3.LocaleUtils;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.LocaleContextResolver;

import java.util.Locale;

/**
 * <P><B>国际化解析:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月10日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class CustomAcceptHeaderLocaleResolver implements LocaleContextResolver {

    private final static LocaleContext DEFAULT_LOCALE=new SimpleLocaleContext(Locale.SIMPLIFIED_CHINESE);

    public CustomAcceptHeaderLocaleResolver() {
    }

    @Override
    public LocaleContext resolveLocaleContext(ServerWebExchange exchange) {
        String header = exchange.getRequest().getHeaders().getFirst("Accept-Language");
        if (header != null) {
            return new SimpleLocaleContext(LocaleUtils.toLocale(header));
        }
        return DEFAULT_LOCALE;
    }

    @Override
    public void setLocaleContext(ServerWebExchange exchange, LocaleContext localeContext) {
        throw new UnsupportedOperationException(
                "Cannot change HTTP Accept-Language header - use a different locale resolution strategy");
    }
}
