package net.github.fastdev.asynctask;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <P><B>任务记录:</B></P>
 * 对外的任务视图，查询时返回。
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月19日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class TaskRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String taskId;

    /**
     * 归属服务名，服务级隔离
     */
    private String service;

    private String businessCode;

    private String tag;

    private Object payload;

    private TaskStatus status;

    private int retryCount;

    private Timestamp nextRunTime;

    /**
     * 集群抢占标识
     */
    private String lockOwner;

    private TaskResult result;

    private String errorMsg;

    private Timestamp createTime;

    private Timestamp updateTime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public Timestamp getNextRunTime() {
        return nextRunTime;
    }

    public void setNextRunTime(Timestamp nextRunTime) {
        this.nextRunTime = nextRunTime;
    }

    public String getLockOwner() {
        return lockOwner;
    }

    public void setLockOwner(String lockOwner) {
        this.lockOwner = lockOwner;
    }

    public TaskResult getResult() {
        return result;
    }

    public void setResult(TaskResult result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
