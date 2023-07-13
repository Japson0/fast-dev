package net.evecom.custom.hdfs.exception;

import net.evecom.fastdev.common.exception.CommonError;
import net.evecom.fastdev.common.exception.CommonException;

/**
 * <P><B>HdfsIo异常:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年10月12日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class HdfsIoException extends CommonException {

    public HdfsIoException(String msg) {
        super(msg);
    }

    public HdfsIoException(Throwable throwable, String msg) {
        super(throwable, CommonError.SYSTEM_FAILED_TO_READ_DISK_FILE.getCode(), msg);
    }
}
