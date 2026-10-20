package net.github.fastdev.asynctask;

/**
 * <P><B>任务状态:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月19日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public enum TaskStatus {


    /**
     * 待执行
     */
    PENDING,

    /**
     * 执行中
     */
    RUNNING,

    /**
     * 执行成功
     */
    SUCCESS,

    /**
     * 执行失败（重试次数用尽）
     */
    FAILED,

    /**
     * 执行超时被回收
     */
    EXPIRED
}
