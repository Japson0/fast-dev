
package com.nlecloud.spring.scaffold.filter;

import com.nlecloud.spring.common.AuthConstants;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.common.UserWrapper;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.TextMapGetter;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * <P><B>dubbo用户信息过滤器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月30日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER})
public class DubboRpcUserContentFilter implements Filter ,BaseFilter.Listener{

    private final static Logger LOGGER= LoggerFactory.getLogger(DubboRpcUserContentFilter.class);

    private final static Tracer TRACER = GlobalOpenTelemetry.getTracer("dubbo-rpc-filter");



    private static final TextMapGetter<Map<String,Object>> GETTER= new TextMapGetter<>() {
        @Override
        public Iterable<String> keys(Map<String, Object> stringObjectMap) {
            return stringObjectMap.keySet();
        }

        @Override
        public String get(Map<String, Object> stringObjectMap, String s) {
            Object value = stringObjectMap.get(s);
            return value == null ? null : value.toString();
        }
    };
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        if (RpcContext.getContext().isConsumerSide()) {
            pushUser(invocation);
        } else {
            popUser(invocation);
        }
        return invoker.invoke(invocation);
    }

    private void pushUser(Invocation invocation) {
        Long userId = UserContext.getUserId();
        if(userId!=null) {
            // 使用 setObjectAttachment 传递对象（推荐方式）
            invocation.setObjectAttachment(AuthConstants.USER_ID_HEADER, userId);
            invocation.setObjectAttachment(AuthConstants.USER_HEADER,UserContext.getUserName());
            invocation.setObjectAttachment(AuthConstants.TENANT_ID_HEADER, UserContext.getTenantId());
            invocation.setObjectAttachment(AuthConstants.SCHOOL_ID_HEADER, UserContext.getSchoolId());
            invocation.setObjectAttachment(AuthConstants.ROLE_HEADER,UserContext.getRoles());
        }
    }

    private void popUser(Invocation invocation) {
        Object userId = invocation.getObjectAttachment(AuthConstants.USER_ID_HEADER);
        if(userId!=null){
            Long tenantId = (Long) invocation.getObjectAttachment(AuthConstants.TENANT_ID_HEADER);
            String username = (String) invocation.getObjectAttachment(AuthConstants.USER_HEADER);
            Long schoolId = (Long) invocation.getObjectAttachment(AuthConstants.SCHOOL_ID_HEADER);
            Collection<String> roles =(Collection<String>) invocation.getObjectAttachment(AuthConstants.ROLE_HEADER);
            UserContext.setUserInfo(new UserWrapper((Long)userId,username,tenantId,schoolId,roles));
        }
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        if(appResponse.hasException()){
            Throwable e = appResponse.getException();
            Context parentContext = W3CTraceContextPropagator.getInstance().extract(Context.current(), RpcContext.getContext().getObjectAttachments(), GETTER);
            // 3. 验证父上下文是否有效
            Span parentSpan = Span.fromContext(parentContext);
            if (!parentSpan.getSpanContext().isValid()) {
                LOGGER.error("dubbo接口执行失败，具体原因:{}",e.getMessage());
                return;
            }
            // Step 2: 创建一个新的 Span，并继承父 SpanContext
            // 创建新Span并绑定到父上下文
            Span errorSpan = TRACER.spanBuilder("DubboRpcError") // 设置 Span 名称
                    .setParent(parentContext) // 设置父 Span
                    .setAttribute("error.type", e.getClass().getName()) // 记录错误类型
                    .setAttribute("error.message", e.getMessage()) // 记录错误消息
                    .startSpan(); // 启动 Span
            try (Scope scope = errorSpan.makeCurrent()) {
                // 4. 在 Span 上下文中记录日志
                LOGGER.error("dubbo接口执行失败，具体原因:{}",e.getMessage());
            } finally {
                // 5. 确保 Span 被正确结束
                errorSpan.end();
            }
        }
    }

    @Override
    public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {
        LOGGER.error("Dubbo RPC error occurred in filter chain", t);

    }
}
