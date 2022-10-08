/*
 * Copyright (c) 2005, 2019, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.fastdev.boot.handle;

import net.evecom.fastdev.common.exception.CommonError;
import net.evecom.fastdev.common.exception.CommonException;
import net.evecom.fastdev.common.model.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

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

    /**
     * 错误日志追中ID
     */
    private final TraceService traceService;

    public GlobalExceptionHandle(TraceService traceService) {
        this.traceService = traceService;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object customerExceptionHandler(HttpServletRequest request, Exception e, HttpServletResponse response) {
        //系统级异常，错误码固定为-1，提示语固定为系统繁忙，请稍后再试
        RestResponse result;
        if (e instanceof CommonException) {
            String code = ((CommonException) e).getCode();
            if (code != null) {
                result = RestResponse.renderError(code, e.getMessage());
            } else {
                result = RestResponse.renderError(CommonError.SYSTEM_RESOURCE_EXCEPTION.getCode(), e.getMessage());
            }
            if (e.getCause() == null) {
                LOGGER.warn("系统业务处理异常：请求：{} ,异常信息:{}", request.getRequestURI(), e.getMessage());
            } else {
                LOGGER.warn("系统业务处理异常：请求：{} ,异常信息:{}", request.getRequestURI(), e.getMessage(), e);
            }
        } else if (e.getClass() == MethodArgumentNotValidException.class) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            result = RestResponse.renderError(CommonError.SYSTEM_RESOURCE_EXCEPTION.getCode(),
                    printValidError((MethodArgumentNotValidException) e));
        } else if (e.getClass() == ConstraintViolationException.class) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            result = RestResponse.renderError(CommonError.SYSTEM_RESOURCE_EXCEPTION.getCode(),
                    printValidateError((ConstraintViolationException) e));
        } else if (e instanceof RuntimeException) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            result = RestResponse.renderError(CommonError.SYSTEM_RESOURCE_EXCEPTION.getCode(), e.getMessage());
            LOGGER.warn("系统运行时异常：请求：{} ,异常信息:{}", request.getRequestURI(), e.getMessage(), e);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            result = RestResponse.renderError(CommonError.SYSTEM_RESOURCE_EXCEPTION.getCode(), "系统未知异常");
            LOGGER.error("系统异常：请求：{} ,异常信息:{}", request.getRequestURI(), e.getMessage(), e);
        }
        result.setTraceId(traceService.getTraceId());
        return result;
    }

    private String printValidateError(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringBuilder message = new StringBuilder(100);
        for (ConstraintViolation<?> m : constraintViolations) {
            message.append(m.getPropertyPath()).append(":").append(m.getMessage()).append(";");
        }
        return message.toString();
    }

    private String printValidError(MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder(100);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            message.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append(";");
        }
        return message.toString();
    }


}
