package net.github.fastdev.asynctask.jdbc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.github.fastdev.asynctask.AsyncTaskProperties;
import net.github.fastdev.asynctask.TaskQuery;
import net.github.fastdev.asynctask.TaskRecord;
import net.github.fastdev.asynctask.TaskResult;
import net.github.fastdev.asynctask.TaskStatus;
import net.github.fastdev.asynctask.TaskStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <P><B>JDBC任务存储:</B></P>
 * 基于JdbcTemplate实现，SQL按方言解析，UPDATE CAS实现集群抢占。
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月20日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class JdbcTaskStore implements TaskStore {


    private static final Logger logger = LoggerFactory.getLogger(JdbcTaskStore.class);

    private static final RowMapper<TaskRecord> ROW_MAPPER = new TaskRecordRowMapper();

    private static final String[] INSERT_COLUMNS = {
            "TASK_ID", "SERVICE", "BUSINESS_CODE", "TAG", "PAYLOAD", "STATUS", "RETRY_COUNT",
            "NEXT_RUN_TIME", "LOCK_OWNER", "RESULT", "ERROR_MSG", "CREATE_TIME", "UPDATE_TIME"
    };

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final ObjectMapper objectMapper;

    private final AsyncTaskProperties properties;

    public JdbcTaskStore(NamedParameterJdbcTemplate jdbcTemplate, ObjectMapper objectMapper,
                         AsyncTaskProperties properties) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
        this.properties = properties;
    }

    @Override
    public String save(TaskRecord record) {
        String taskId = record.getTaskId();
        if (taskId == null || taskId.trim().isEmpty()) {
            taskId = java.util.UUID.randomUUID().toString().replace("-", "");
            record.setTaskId(taskId);
        }
        if (record.getStatus() == null) {
            record.setStatus(TaskStatus.PENDING);
        }
        record.setService(properties.getNamespace());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", taskId);
        params.put("service", properties.getNamespace());
        params.put("businessCode", record.getBusinessCode());
        params.put("tag", record.getTag());
        params.put("payload", record.getPayload() == null ? null : String.valueOf(record.getPayload()));
        params.put("status", record.getStatus().name());
        params.put("retryCount", record.getRetryCount());
        params.put("nextRunTime", record.getNextRunTime());
        params.put("lockOwner", record.getLockOwner());
        params.put("result", record.getResult() == null ? null : toJson(record.getResult()));
        params.put("errorMsg", record.getErrorMsg());
        params.put("createTime", now);
        params.put("updateTime", now);
        StringBuilder sql = new StringBuilder("INSERT INTO async_task_record (");
        StringBuilder values = new StringBuilder(" VALUES (");
        List<String> columns = new ArrayList<>();
        for (String column : INSERT_COLUMNS) {
            columns.add(column);
        }
        sql.append(String.join(", ", columns)).append(")");
        values.append(":taskId, :service, :businessCode, :tag, :payload, :status, :retryCount, ")
                .append(":nextRunTime, :lockOwner, :result, :errorMsg, :createTime, :updateTime)");
        jdbcTemplate.update(sql.append(values).toString(), params);
        return taskId;
    }

    @Override
    public TaskRecord poll(String lockOwner) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String selectSql = "SELECT * FROM async_task_record WHERE SERVICE=:service AND STATUS='PENDING' " +
                "AND (NEXT_RUN_TIME IS NULL OR NEXT_RUN_TIME<=:now) ORDER BY CREATE_TIME LIMIT 1";
        TaskRecord record;
        try {
            record = jdbcTemplate.queryForObject(selectSql,
                    new MapSqlParameterSource("now", now)
                            .addValue("service", properties.getNamespace()), ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        if (record == null) {
            return null;
        }
        String casSql = "UPDATE async_task_record SET STATUS='RUNNING', LOCK_OWNER=:lockOwner, UPDATE_TIME=:now " +
                "WHERE TASK_ID=:taskId AND STATUS='PENDING'";
        Map<String, Object> casParams = new HashMap<>();
        casParams.put("lockOwner", lockOwner);
        casParams.put("now", now);
        casParams.put("taskId", record.getTaskId());
        int won = jdbcTemplate.update(casSql, casParams);
        if (won != 1) {
            return null;
        }
        record.setStatus(TaskStatus.RUNNING);
        record.setLockOwner(lockOwner);
        return record;
    }

    @Override
    public void updateStatus(String taskId, TaskStatus status, TaskResult result, String errorMsg) {
        String sql = "UPDATE async_task_record SET STATUS=:status, UPDATE_TIME=:now";
        Map<String, Object> params = new HashMap<>();
        params.put("status", status.name());
        params.put("now", new Timestamp(System.currentTimeMillis()));
        if (result != null) {
            sql += ", RESULT=:result";
            params.put("result", toJson(result));
        }
        if (errorMsg != null) {
            sql += ", ERROR_MSG=:errorMsg";
            params.put("errorMsg", errorMsg);
        }
        sql += " WHERE TASK_ID=:taskId";
        params.put("taskId", taskId);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public boolean scheduleRetry(String taskId, int maxRetry) {
        String selectSql = "SELECT RETRY_COUNT FROM async_task_record WHERE TASK_ID=:taskId";
        Integer retryCount;
        try {
            retryCount = jdbcTemplate.queryForObject(selectSql,
                    new MapSqlParameterSource("taskId", taskId), Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        if (retryCount == null) {
            retryCount = 0;
        }
        if (retryCount >= maxRetry) {
            return false;
        }
        long backoff = properties.getRetry().getBackoffMillis();
        String sql = "UPDATE async_task_record SET STATUS='PENDING', LOCK_OWNER=NULL, " +
                "RETRY_COUNT=:retryCount, NEXT_RUN_TIME=:nextRunTime, UPDATE_TIME=:now WHERE TASK_ID=:taskId";
        Map<String, Object> params = new HashMap<>();
        params.put("retryCount", retryCount + 1);
        params.put("nextRunTime", new Timestamp(System.currentTimeMillis() + backoff));
        params.put("now", new Timestamp(System.currentTimeMillis()));
        params.put("taskId", taskId);
        jdbcTemplate.update(sql, params);
        return true;
    }

    @Override
    public int recycleExpired(long leaseMillis) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp deadline = new Timestamp(now.getTime() - leaseMillis);
        String sql = "UPDATE async_task_record SET STATUS='PENDING', LOCK_OWNER=NULL, RETRY_COUNT=RETRY_COUNT+1, " +
                "UPDATE_TIME=:now WHERE SERVICE=:service AND STATUS='RUNNING' AND UPDATE_TIME<:deadline";
        Map<String, Object> params = new HashMap<>();
        params.put("now", now);
        params.put("service", properties.getNamespace());
        params.put("deadline", deadline);
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public TaskRecord get(String taskId) {
        String sql = "SELECT * FROM async_task_record WHERE TASK_ID=:taskId";
        try {
            return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("taskId", taskId), ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<TaskRecord> query(TaskQuery query) {
        StringBuilder sql = new StringBuilder("SELECT * FROM async_task_record WHERE SERVICE=:service");
        MapSqlParameterSource params = new MapSqlParameterSource("service", properties.getNamespace());
        if (query.getBusinessCode() != null && !query.getBusinessCode().trim().isEmpty()) {
            sql.append(" AND BUSINESS_CODE=:businessCode");
            params.addValue("businessCode", query.getBusinessCode());
        }
        if (query.getTag() != null && !query.getTag().trim().isEmpty()) {
            sql.append(" AND TAG=:tag");
            params.addValue("tag", query.getTag());
        }
        Collection<TaskStatus> statuses = query.getStatuses();
        if (statuses != null && !statuses.isEmpty()) {
            sql.append(" AND STATUS IN (:statuses)");
            params.addValue("statuses", statusNames(statuses));
        }
        if (query.getCreateTimeFrom() != null) {
            sql.append(" AND CREATE_TIME>=:createTimeFrom");
            params.addValue("createTimeFrom", query.getCreateTimeFrom());
        }
        if (query.getCreateTimeTo() != null) {
            sql.append(" AND CREATE_TIME<=:createTimeTo");
            params.addValue("createTimeTo", query.getCreateTimeTo());
        }
        sql.append(" ORDER BY CREATE_TIME DESC");
        return jdbcTemplate.query(sql.toString(), params, ROW_MAPPER);
    }

    private List<String> statusNames(Collection<TaskStatus> statuses) {
        List<String> names = new ArrayList<>();
        for (TaskStatus status : statuses) {
            names.add(status.name());
        }
        return names;
    }

    private String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("结果序列化失败:" + e.getMessage(), e);
        }
    }

    private TaskResult fromJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(json, TaskResult.class);
        } catch (Exception e) {
            logger.warn("结果反序列化失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * <P><B>任务记录行映射:</B></P>
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月20日 CREATE
     *
     * @author Japson Huang
     * @version1.0
     */
    private static class TaskRecordRowMapper implements RowMapper<TaskRecord> {

        private static final ObjectMapper MAPPER = new ObjectMapper();

        @Override
        public TaskRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
            TaskRecord record = new TaskRecord();
            record.setTaskId(rs.getString("TASK_ID"));
            record.setService(rs.getString("SERVICE"));
            record.setBusinessCode(rs.getString("BUSINESS_CODE"));
            record.setTag(rs.getString("TAG"));
            record.setPayload(rs.getString("PAYLOAD"));
            String status = rs.getString("STATUS");
            record.setStatus(status == null ? null : TaskStatus.valueOf(status));
            int retryCount = rs.getInt("RETRY_COUNT");
            record.setRetryCount(rs.wasNull() ? 0 : retryCount);
            record.setNextRunTime(rs.getTimestamp("NEXT_RUN_TIME"));
            record.setLockOwner(rs.getString("LOCK_OWNER"));
            record.setResult(fromJsonStatic(rs.getString("RESULT")));
            record.setErrorMsg(rs.getString("ERROR_MSG"));
            record.setCreateTime(rs.getTimestamp("CREATE_TIME"));
            record.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
            return record;
        }

        private TaskResult fromJsonStatic(String json) {
            if (json == null || json.trim().isEmpty()) {
                return null;
            }
            try {
                return MAPPER.readValue(json, TaskResult.class);
            } catch (Exception e) {
                return null;
            }
        }
    }
}
