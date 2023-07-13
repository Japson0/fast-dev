/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.common.exception;

/**
 * <P><B>资源找不到异常:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年12月08日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class ResourceNotFoundException extends ResourceException {

    /**
     * 信息
     */
    private static final String MSG = "%s资源找不到，请确认！！！";

    /**
     * 码值
     */
    private static final String CODE = CommonError.RESOURCE_NOT_FOUND.getCode();


    public ResourceNotFoundException(String resourceName) {
        super(CODE, String.format(MSG, resourceName));
    }

    public ResourceNotFoundException(Throwable throwable, String resourceName) {
        super(throwable, CODE, String.format(MSG, resourceName));
    }

    public ResourceNotFoundException() {
        super(CODE, "数据资源不存在，请确认！！！");
    }
}
