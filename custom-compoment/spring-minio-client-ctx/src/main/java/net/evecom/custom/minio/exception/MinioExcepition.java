/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.custom.minio.exception;

import net.evecom.fastdev.common.exception.CommonError;
import net.evecom.fastdev.common.exception.CommonException;

/**
 * MinioExcepition
 *
 * @author Nick Lv
 * @created 2022/10/12 17:48
 */
public class MinioExcepition extends CommonException {

    public MinioExcepition(String msg) {
        super(msg);
    }

    public MinioExcepition(Throwable throwable, String msg) {
        super(throwable, CommonError.SYSTEM_FAILED_TO_READ_DISK_FILE.getCode(), msg);
    }
}



