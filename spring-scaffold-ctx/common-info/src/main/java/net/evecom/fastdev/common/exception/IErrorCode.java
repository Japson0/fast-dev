package net.evecom.fastdev.common.exception;

/**
 * 异常编码接口。
 *
 * @author 创建人：jim
 * @version 版本号：V1.0
 * @date 创建日期：2019/11/8
 * @Description 功能说明：
 */
public interface IErrorCode {


    /**
     * 错误编码 0、正常  1、失败
     *
     * @return
     */
    String getCode();

    /**
     * 错误描述
     *
     * @return
     */
    String getMsg();

    /**
     * 转换成异常类
     *
     * @return
     */
    Exception toException();

    /**
     * 转换成异常类
     *
     * @param args
     * @return
     */
    Exception toException(Object... args);

}
