package com.nlecloud.spring.webflux.scaffold;

import com.necloud.spring.common.AuthConstants;
import com.nlecloud.spring.webflux.scaffold.filter.UserWrapper;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <P><B>dubbo服务代理:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月18日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class DubboServiceProxy<T> {

    private final T api;

    public DubboServiceProxy(T api) {
        this.api = api;
    }

    public <R> Mono<R> invoke(Function<T,R> supplier){

        return Mono.deferContextual(contextView -> {
            return Mono.fromCallable(() -> {
                // 这部分代码会在 boundedElastic 线程池执行
                UserWrapper userWrapper = UserWrapper.getUserWrapper(contextView);
                if(userWrapper!=null){
                    RpcContext.getContext().setAttachment(AuthConstants.USER_HEADER, userWrapper.getUsername());
                    RpcContext.getContext().setAttachment(AuthConstants.USER_ID_HEADER, userWrapper.getUserId());
                    RpcContext.getContext().setAttachment(AuthConstants.USER_ID_HEADER, userWrapper.getUserId());
                    RpcContext.getContext().setAttachment(AuthConstants.TENANT_ID_HEADER, String.join(",", userWrapper.getRoles()));
                }
                return supplier.apply(api);
            }).subscribeOn(Schedulers.boundedElastic()); // 在这里指定
        });
    }
}
