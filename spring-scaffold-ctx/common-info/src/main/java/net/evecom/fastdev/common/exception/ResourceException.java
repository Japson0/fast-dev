/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.common.exception;

/**
 * <P><B>资源异常:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年12月08日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ResourceException extends CommonException {

    private static final long serialVersionUID = -2883592666172747118L;

    public ResourceException(String msg) {
        super(CommonError.USER_RESOURCE_EXCEPTION.getCode(), msg);
    }

    public ResourceException(Throwable throwable, String msg) {
        super(throwable, CommonError.USER_RESOURCE_EXCEPTION.getCode(), msg);
    }

    public ResourceException(Throwable throwable, String msg, Object... data) {
        super(throwable, CommonError.USER_RESOURCE_EXCEPTION.getCode(), msg, data);
    }

    public ResourceException(String msg, Object... data) {
        super(CommonError.USER_RESOURCE_EXCEPTION.getCode(), msg, data);
    }

}
