package net.evecom.fastdev.ddp.handle;

import net.evecom.fastdev.boot.handle.TraceService;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年09月29日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class LoggerTracesService implements TraceService {
    @Override
    public String getTraceId() {
        return TraceContext.traceId();
    }
}
