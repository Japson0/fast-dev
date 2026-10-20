package net.github.fastdev.asynctask;

import java.util.List;

/**
 * <P><B>任务存储抽象:</B></P>
 * 屏蔽底层存储差异，redis与jdbc各自实现。
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月19日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface TaskStore {


    /**
     * 保存任务，初始状态PENDING，并入队
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     */
    String save(TaskRecord record);

    /**
     * 取出一条待执行任务（抢占语义，多实例只有一个成功）
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     * @return 抢占成功返回任务，无任务返回null
     */
    TaskRecord poll(String lockOwner);

    /**
     * 更新任务状态
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     */
    void updateStatus(String taskId, TaskStatus status, TaskResult result, String errorMsg);

    /**
     * 任务执行失败后的重试安排，重试次数+1并设置下次执行时间
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     * @return false表示重试次数用尽
     */
    boolean scheduleRetry(String taskId, int maxRetry);

    /**
     * 回收超时RUNNING任务，重置为PENDING重试
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     * @return 回收的任务数
     */
    int recycleExpired(long leaseMillis);

    /**
     * 查询单个任务
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     */
    TaskRecord get(String taskId);

    /**
     * 按条件查询任务
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     */
    List<TaskRecord> query(TaskQuery query);
}
