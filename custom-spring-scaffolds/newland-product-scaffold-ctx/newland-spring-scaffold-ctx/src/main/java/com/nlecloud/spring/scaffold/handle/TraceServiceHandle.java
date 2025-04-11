package com.nlecloud.spring.scaffold.handle;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Context;
import net.github.fastdev.boot.handle.TraceService;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月11日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class TraceServiceHandle implements TraceService {
    @Override
    public String getTraceId() {
        Span currentSpan = Span.fromContext(Context.current());
        return currentSpan.getSpanContext().getTraceId();
    }
}
