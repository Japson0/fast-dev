
package com.nlecloud.spring.webflux.scaffold;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Context;
import net.github.fastdev.common.exception.CommonError;
import net.github.fastdev.common.exception.CommonException;
import net.github.fastdev.common.model.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <P><B>Description: </B> 定义全局性异常处理类  </P>
 * Revision Trail: (Date/Author/Description)
 * 2019/3/20 Japson Huang CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandle {

    /**
     * 日志器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandle.class);


    @ExceptionHandler(CommonException.class)
    public Mono<ResponseEntity<RestResponse>> handleAllExceptions(CommonException ex,ServerWebExchange exchange) {

        LOGGER.error("调用接口：{}，出错,具体他原因：{}",exchange.getRequest().getPath(), ex.getMessage(),ex);
        ServerHttpResponse response = exchange.getResponse();
        response.beforeCommit(() -> {
            response.getHeaders().add("X-Trace-ID", getTraceId());
            return Mono.empty();
        });
        return Mono.just(ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(RestResponse.renderError(ex.getCode(),ex.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<RestResponse>> handleAllExceptions(Exception ex,ServerWebExchange exchange) {
        LOGGER.error("调用接口：{}，出错,具体他原因：{}",exchange.getRequest().getPath(), ex.getMessage(),ex);
        ServerHttpResponse response = exchange.getResponse();
        response.beforeCommit(() -> {
            response.getHeaders().add("X-Trace-ID", getTraceId());
            return Mono.empty();
        });
        return Mono.just(ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(RestResponse.renderError(CommonError.SYSTEM_RESOURCE_EXCEPTION)));
    }


    private String getTraceId() {
        Span currentSpan = Span.fromContext(Context.current());
        return currentSpan.getSpanContext().getTraceId();
    }
}
