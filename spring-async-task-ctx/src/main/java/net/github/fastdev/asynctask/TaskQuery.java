package net.github.fastdev.asynctask;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * <P><B>任务查询条件:</B></P>
 * 支持按businessCode、tag、状态过滤。
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月19日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class TaskQuery {


    /**
     * 任务业务编码，精确匹配
     */
    private String businessCode;

    /**
     * 过滤标签，精确匹配
     */
    private String tag;

    /**
     * 状态列表
     */
    private Collection<TaskStatus> statuses;

    /**
     * 创建时间起
     */
    private Timestamp createTimeFrom;

    /**
     * 创建时间止
     */
    private Timestamp createTimeTo;

    public static TaskQuery of(String businessCode) {
        TaskQuery query = new TaskQuery();
        query.businessCode = businessCode;
        return query;
    }

    public static TaskQuery of(String businessCode, String tag) {
        TaskQuery query = new TaskQuery();
        query.businessCode = businessCode;
        query.tag = tag;
        return query;
    }

    public TaskQuery tag(String tag) {
        this.tag = tag;
        return this;
    }

    public TaskQuery statuses(TaskStatus... statuses) {
        this.statuses = statuses == null ? null : Arrays.asList(statuses);
        return this;
    }

    public TaskQuery createTimeBetween(Timestamp from, Timestamp to) {
        this.createTimeFrom = from;
        this.createTimeTo = to;
        return this;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public String getTag() {
        return tag;
    }

    public Collection<TaskStatus> getStatuses() {
        return statuses;
    }

    public Timestamp getCreateTimeFrom() {
        return createTimeFrom;
    }

    public Timestamp getCreateTimeTo() {
        return createTimeTo;
    }
}
