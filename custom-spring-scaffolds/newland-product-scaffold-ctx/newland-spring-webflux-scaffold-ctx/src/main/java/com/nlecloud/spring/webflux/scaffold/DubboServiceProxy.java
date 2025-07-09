package com.nlecloud.spring.webflux.scaffold;

import com.nlecloud.spring.common.AuthConstants;
import com.nlecloud.spring.webflux.scaffold.filter.UserWrapper;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import org.apache.dubbo.rpc.RpcContext;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * <P><B>dubbo服务代理:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月18日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class DubboServiceProxy<T> {

    private final T api;

    public DubboServiceProxy(T api) {
        this.api = api;
    }

    public <R> Mono<R> invoke(Function<T, R> supplier) {
        // 主线程中拷贝当前 RpcContext 的附件
        Map<String, String> attachments = new HashMap<>(RpcContext.getContext().getAttachments());

        return Mono.deferContextual(contextView -> {
            UserWrapper userWrapper = UserWrapper.getUserWrapper(contextView);
            if (userWrapper != null) {
                attachments.put(AuthConstants.USER_HEADER, userWrapper.getUsername());
                attachments.put(AuthConstants.USER_ID_HEADER, userWrapper.getUserId().toString());
                attachments.put(AuthConstants.TENANT_ID_HEADER, userWrapper.getTenantId().toString());
                attachments.put(AuthConstants.ROLE_HEADER, String.join(",", userWrapper.getRoles()));
            }

            // 获取当前线程的 OpenTelemetry 上下文
            Context otelContext = Context.current();

            return Mono.fromCallable(() -> {
                        // 恢复 OpenTelemetry 上下文
                        try (Scope ignored = otelContext.makeCurrent()) {
                            RpcContext rpcContext = RpcContext.getContext();
                            try {
                                rpcContext.setAttachments(new HashMap<>(attachments));
                                return supplier.apply(api);
                            } finally {
                                rpcContext.clearAttachments(); // 恢复原 attachments
                            }
                        }
                    })
                    .onErrorResume(ex -> Mono.error(new RuntimeException("Dubbo service invoke failed", ex)))
                    .subscribeOn(Schedulers.boundedElastic());
        });
    }
}