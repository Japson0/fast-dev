package com.nlecloud.spring.webflux.scaffold;

import com.nlecloud.spring.common.AuthConstants;
import com.nlecloud.spring.webflux.scaffold.filter.UserWrapper;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcServiceContext;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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
        // 1. 捕获当前 Reactor Context 中的 UserWrapper
        return Mono.deferContextual(contextView -> {
            UserWrapper userWrapper = UserWrapper.getUserWrapper(contextView);
            Context otelContext = Context.current(); // 捕获当前 OTel 上下文

            // 2. 构建要传递的 attachments
            return Mono.fromCallable(() -> {
                        // 3. 在 callable 线程中：设置 Dubbo 上下文
                        RpcServiceContext serviceContext = RpcContext.getServiceContext();

                        // 使用 setObjectAttachment，推荐方式
                        if (userWrapper != null) {
                            serviceContext.setObjectAttachment(AuthConstants.USER_HEADER, userWrapper.getUsername());
                            serviceContext.setObjectAttachment(AuthConstants.USER_ID_HEADER, userWrapper.getUserId());
                            serviceContext.setObjectAttachment(AuthConstants.TENANT_ID_HEADER, userWrapper.getTenantId());
                            serviceContext.setObjectAttachment(AuthConstants.ROLE_HEADER, userWrapper.getRoles());
                            serviceContext.setObjectAttachment(AuthConstants.SCHOOL_ID_HEADER, userWrapper.getSchoolId());
                        }

                        // 4. 恢复 OpenTelemetry 上下文
                        try (Scope scope = otelContext.makeCurrent()) {
                            // 执行 Dubbo 调用
                            return supplier.apply(api);
                        } catch (Throwable t) {
                            throw new RuntimeException("Dubbo service invoke failed", t);
                        }
                    })
                    .onErrorMap(t -> new RuntimeException("Dubbo service invoke failed", t))
                    .subscribeOn(Schedulers.boundedElastic());
        });
    }
}