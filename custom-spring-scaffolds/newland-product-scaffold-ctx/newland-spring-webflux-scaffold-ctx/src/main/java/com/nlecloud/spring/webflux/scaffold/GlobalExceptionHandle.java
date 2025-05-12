
package com.nlecloud.spring.webflux.scaffold;

import com.necloud.spring.common.RestResult;
import net.github.fastdev.common.exception.CommonError;
import net.github.fastdev.common.exception.CommonException;
import net.github.fastdev.common.model.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <P><B>Description: </B> 定义全局性异常处理类  </P>
 * Revision Trail: (Date/Author/Description)
 * 2019/3/20 Timer He CREATE
 *
 * @author Timer He
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
        LOGGER.error("调用接口：{}，出错",exchange.getRequest().getPath(),ex);
        return Mono.just(ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(RestResponse.renderError(ex.getCode(),ex.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<RestResponse>> handleAllExceptions(Exception ex,ServerWebExchange exchange) {
        LOGGER.error("调用接口：{}，出错",exchange.getRequest().getPath(),ex);

        return Mono.just(ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(RestResponse.renderError(CommonError.SYSTEM_RESOURCE_EXCEPTION)));
    }
}
