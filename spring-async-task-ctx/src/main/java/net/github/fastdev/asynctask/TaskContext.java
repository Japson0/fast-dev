package net.github.fastdev.asynctask;

import java.util.Map;

/**
 * <P><B>任务上下文:</B></P>
 * 任务执行时提供给Handler的上下文，payload从存储反序列化而来。
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月19日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class TaskContext {


    /**
     * 任务ID
     */
    private final String taskId;

    /**
     * 任务业务编码
     */
    private final String businessCode;

    /**
     * 过滤标签
     */
    private final String tag;

    /**
     * 业务参数
     */
    private final Map<String, Object> payload;

    public TaskContext(String taskId, String businessCode, String tag, Map<String, Object> payload) {
        this.taskId = taskId;
        this.businessCode = businessCode;
        this.tag = tag;
        this.payload = payload;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public String getTag() {
        return tag;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    /**
     * 获取参数
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     */
    public Object get(String key) {
        return payload == null ? null : payload.get(key);
    }

    /**
     * 获取字符串参数
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     */
    public String getString(String key) {
        Object value = get(key);
        return value == null ? null : String.valueOf(value);
    }

    /**
     * 获取Long参数，数字类型自动转换
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     */
    public Long getLong(String key) {
        Object value = get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        return Long.valueOf(String.valueOf(value));
    }

    /**
     * 获取Integer参数，数字类型自动转换
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月19日
     *
     * @author Japson Huang
     */
    public Integer getInteger(String key) {
        Object value = get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return Integer.valueOf(String.valueOf(value));
    }
}
