package net.github.fastdev.asynctask;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * <P><B>任务结果:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月19日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class TaskResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    private boolean success = true;

    /**
     * 结果数据，需要可JSON序列化
     */
    private Object data;

    public TaskResult() {
    }

    public TaskResult(Object data) {
        this.data = data;
    }

    public static TaskResult success() {
        return new TaskResult();
    }

    public static TaskResult success(Object data) {
        return new TaskResult(data);
    }

    public static TaskResult failure() {
        TaskResult result = new TaskResult();
        result.success = false;
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
