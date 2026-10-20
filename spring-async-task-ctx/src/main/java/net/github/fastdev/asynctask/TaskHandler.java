package net.github.fastdev.asynctask;

/**
 * <P><B>任务处理器:</B></P>
 * 任务的实际执行逻辑载体，业务方实现此接口并注册为Spring bean。
 * businessCode同时作为路由键与过滤维度。
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月19日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface TaskHandler {


    /**
     * 任务业务编码，全局唯一。
     * 既是任务的路由键（按此查找Handler），也是任务列表的过滤维度。
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     */
    String businessCode();

    /**
     * 执行任务
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     * @param context 任务上下文，包含payload参数
     * @return 任务结果，会持久化到存储
     */
    TaskResult handle(TaskContext context) throws Exception;
}
