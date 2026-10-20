package net.github.fastdev.asynctask;

import java.time.Duration;
import java.util.List;

/**
 * <P><B>异步任务引擎:</B></P>
 * 通用异步队列执行器，支持redis与jdbc两种底层存储。
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月19日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface TaskEngine {


    /**
     * 提交异步任务，任务持久化后由工作线程异步执行
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     * @param handler 任务处理器，businessCode()作为路由键与过滤维度
     * @param tag 过滤标签，可为null
     * @param payload 任务业务参数，需要可JSON序列化
     * @return 任务ID
     */
    String submit(TaskHandler handler, String tag, Object payload);

    /**
     * 阻塞等待任务结果
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     * @param taskId 任务ID
     * @param timeout 等待超时时间
     * @return 任务结果，超时或任务不存在返回null
     */
    TaskResult await(String taskId, Duration timeout);

    /**
     * 按条件查询任务
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     */
    List<TaskRecord> query(TaskQuery query);
}
