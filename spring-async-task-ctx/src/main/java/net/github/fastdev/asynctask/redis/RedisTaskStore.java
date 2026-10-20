package net.github.fastdev.asynctask.redis;

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
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <P><B>Redis任务存储:</B></P>
 * List做队列（CAS抢占），Hash存元数据，Set做businessCode/tag索引。
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月19日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class RedisTaskStore implements TaskStore {


    private static final Logger logger = LoggerFactory.getLogger(RedisTaskStore.class);

    private static final String FIELD_TASK_ID = "taskId";
    private static final String FIELD_BUSINESS_CODE = "businessCode";
    private static final String FIELD_TAG = "tag";
    private static final String FIELD_PAYLOAD = "payload";
    private static final String FIELD_STATUS = "status";
    private static final String FIELD_RETRY_COUNT = "retryCount";
    private static final String FIELD_NEXT_RUN_TIME = "nextRunTime";
    private static final String FIELD_RESULT = "result";
    private static final String FIELD_ERROR_MSG = "errorMsg";
    private static final String FIELD_CREATE_TIME = "createTime";
    private static final String FIELD_UPDATE_TIME = "updateTime";
    private static final String FIELD_LOCK_OWNER = "lockOwner";

    private final RedisTemplate<String, String> redisTemplate;

    private final ObjectMapper objectMapper;

    private final AsyncTaskProperties properties;

    public RedisTaskStore(RedisTemplate<String, String> redisTemplate, ObjectMapper objectMapper,
                          AsyncTaskProperties properties) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
        this.properties = properties;
    }

    private String queueKey() {
        return properties.getNamespace() + ":queue";
    }

    private String metaKey(String taskId) {
        return properties.getNamespace() + ":meta:" + taskId;
    }

    private String codeIndexKey(String businessCode) {
        return properties.getNamespace() + ":idx:code:" + businessCode;
    }

    private String tagIndexKey(String tag) {
        return properties.getNamespace() + ":idx:tag:" + tag;
    }

    @Override
    public String save(TaskRecord record) {
        String taskId = java.util.UUID.randomUUID().toString().replace("-", "");
        record.setTaskId(taskId);
        long now = System.currentTimeMillis();
        Map<String, String> hash = new LinkedHashMap<>();
        hash.put(FIELD_TASK_ID, taskId);
        hash.put(FIELD_BUSINESS_CODE, record.getBusinessCode());
        hash.put(FIELD_TAG, record.getTag());
        hash.put(FIELD_PAYLOAD, (String) record.getPayload());
        hash.put(FIELD_STATUS, TaskStatus.PENDING.name());
        hash.put(FIELD_RETRY_COUNT, "0");
        hash.put(FIELD_CREATE_TIME, String.valueOf(now));
        hash.put(FIELD_UPDATE_TIME, String.valueOf(now));
        redisTemplate.opsForHash().putAll(metaKey(taskId), hash);
        redisTemplate.expire(metaKey(taskId), 30, TimeUnit.DAYS);
        if (record.getTag() != null && !record.getTag().trim().isEmpty()) {
            redisTemplate.opsForSet().add(tagIndexKey(record.getTag()), taskId);
            redisTemplate.expire(tagIndexKey(record.getTag()), 30, TimeUnit.DAYS);
        }
        redisTemplate.opsForSet().add(codeIndexKey(record.getBusinessCode()), taskId);
        redisTemplate.expire(codeIndexKey(record.getBusinessCode()), 30, TimeUnit.DAYS);
        redisTemplate.opsForList().rightPush(queueKey(), taskId);
        return taskId;
    }

    @Override
    public TaskRecord poll(String lockOwner) {
        String taskId = redisTemplate.opsForList().leftPop(queueKey());
        if (taskId == null) {
            return null;
        }
        Map<Object, Object> hash = redisTemplate.opsForHash().entries(metaKey(taskId));
        if (hash.isEmpty()) {
            return null;
        }
        // 抢占：CAS把PENDING改为RUNNING，成功者执行
        String script = "if redis.call('hget', KEYS[1], 'status') == 'PENDING' then " +
                "redis.call('hset', KEYS[1], 'status', 'RUNNING', 'lockOwner', ARGV[1], 'updateTime', ARGV[2]) " +
                "return 1 else return 0 end";
        DefaultRedisScript<Long> casScript = new DefaultRedisScript<>(script, Long.class);
        Long won = redisTemplate.execute(casScript, java.util.Collections.singletonList(metaKey(taskId)),
                lockOwner, String.valueOf(System.currentTimeMillis()));
        if (won == null || won != 1) {
            return null;
        }
        TaskRecord record = mapToRecord(hash, taskId);
        record.setStatus(TaskStatus.RUNNING);
        return record;
    }

    @Override
    public void updateStatus(String taskId, TaskStatus status, TaskResult result, String errorMsg) {
        Map<String, String> updates = new HashMap<>();
        updates.put(FIELD_STATUS, status.name());
        updates.put(FIELD_UPDATE_TIME, String.valueOf(System.currentTimeMillis()));
        if (result != null) {
            updates.put(FIELD_RESULT, toJson(result));
        }
        if (errorMsg != null) {
            updates.put(FIELD_ERROR_MSG, errorMsg);
        }
        redisTemplate.opsForHash().putAll(metaKey(taskId), updates);
    }

    @Override
    public boolean scheduleRetry(String taskId, int maxRetry) {
        Map<Object, Object> hash = redisTemplate.opsForHash().entries(metaKey(taskId));
        if (hash.isEmpty()) {
            return false;
        }
        int retryCount = parseInt(String.valueOf(hash.get(FIELD_RETRY_COUNT)), 0);
        if (retryCount >= maxRetry) {
            return false;
        }
        long backoff = properties.getRetry().getBackoffMillis();
        Map<String, String> updates = new HashMap<>();
        updates.put(FIELD_STATUS, TaskStatus.PENDING.name());
        updates.put(FIELD_RETRY_COUNT, String.valueOf(retryCount + 1));
        updates.put(FIELD_NEXT_RUN_TIME, String.valueOf(System.currentTimeMillis() + backoff));
        updates.put(FIELD_UPDATE_TIME, String.valueOf(System.currentTimeMillis()));
        redisTemplate.opsForHash().putAll(metaKey(taskId), updates);
        redisTemplate.opsForList().rightPush(queueKey(), taskId);
        return true;
    }

    @Override
    public int recycleExpired(long leaseMillis) {
        // 扫描RUNNING超租约任务，重置PENDING并重新入队
        String pattern = properties.getNamespace() + ":meta:*";
        int recycled = 0;
        long deadline = System.currentTimeMillis() - leaseMillis;
        List<String> keys = scanKeys(pattern);
        for (String metaKey : keys) {
            Map<Object, Object> hash = redisTemplate.opsForHash().entries(metaKey);
            if (hash.isEmpty()) {
                continue;
            }
            if (!TaskStatus.RUNNING.name().equals(String.valueOf(hash.get(FIELD_STATUS)))) {
                continue;
            }
            long updateTime = parseLong(String.valueOf(hash.get(FIELD_UPDATE_TIME)), 0L);
            if (updateTime < deadline) {
                String taskId = String.valueOf(hash.get(FIELD_TASK_ID));
                int retryCount = parseInt(String.valueOf(hash.get(FIELD_RETRY_COUNT)), 0);
                Map<String, String> updates = new HashMap<>();
                updates.put(FIELD_STATUS, TaskStatus.PENDING.name());
                updates.put(FIELD_NEXT_RUN_TIME, String.valueOf(System.currentTimeMillis()));
                updates.put(FIELD_UPDATE_TIME, String.valueOf(System.currentTimeMillis()));
                redisTemplate.opsForHash().putAll(metaKey, updates);
                redisTemplate.opsForList().rightPush(queueKey(), taskId);
                recycled++;
                logger.warn("回收超时RUNNING任务:{}", taskId);
            }
        }
        return recycled;
    }

    @Override
    public List<TaskRecord> query(TaskQuery query) {
        Set<String> taskIds = null;
        if (query.getBusinessCode() != null && !query.getBusinessCode().trim().isEmpty()) {
            taskIds = redisTemplate.opsForSet().members(codeIndexKey(query.getBusinessCode()));
        }
        if (query.getTag() != null && !query.getTag().trim().isEmpty()) {
            Set<String> tagIds = redisTemplate.opsForSet().members(tagIndexKey(query.getTag()));
            taskIds = merge(taskIds, tagIds);
        }
        if (taskIds == null) {
            // 无过滤条件时扫描全部元数据
            taskIds = scanKeys(pattern())
                    .stream()
                    .map(key -> key.substring(key.lastIndexOf(':') + 1))
                    .collect(Collectors.toSet());
        }
        List<TaskRecord> records = new ArrayList<>();
        for (String taskId : taskIds) {
            TaskRecord record = get(taskId);
            if (record == null) {
                continue;
            }
            if (matchStatus(query, record) && matchCreateTime(query, record)) {
                records.add(record);
            }
        }
        return records;
    }

    @Override
    public TaskRecord get(String taskId) {
        Map<Object, Object> hash = redisTemplate.opsForHash().entries(metaKey(taskId));
        if (hash.isEmpty()) {
            return null;
        }
        return mapToRecord(hash, taskId);
    }

    private String pattern() {
        return properties.getNamespace() + ":meta:*";
    }

    private boolean matchStatus(TaskQuery query, TaskRecord record) {
        Collection<TaskStatus> statuses = query.getStatuses();
        return statuses == null || statuses.isEmpty() || statuses.contains(record.getStatus());
    }

    private boolean matchCreateTime(TaskQuery query, TaskRecord record) {
        if (query.getCreateTimeFrom() == null && query.getCreateTimeTo() == null) {
            return true;
        }
        Timestamp createTime = record.getCreateTime();
        if (createTime == null) {
            return false;
        }
        if (query.getCreateTimeFrom() != null && createTime.before(query.getCreateTimeFrom())) {
            return false;
        }
        return query.getCreateTimeTo() == null || !createTime.after(query.getCreateTimeTo());
    }

    private Set<String> merge(Set<String> left, Set<String> right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        Set<String> merged = new HashSet<>(left);
        merged.retainAll(right);
        return merged;
    }

    private List<String> scanKeys(String pattern) {
        List<String> keys = new ArrayList<>();
        try (Cursor<String> cursor = redisTemplate.scan(ScanOptions.scanOptions().match(pattern).count(100).build())) {
            while (cursor.hasNext()) {
                keys.add(cursor.next());
            }
        }
        return keys;
    }

    private TaskRecord mapToRecord(Map<Object, Object> hash, String taskId) {
        TaskRecord record = new TaskRecord();
        record.setTaskId(taskId);
        record.setBusinessCode(String.valueOf(hash.get(FIELD_BUSINESS_CODE)));
        record.setTag(hash.get(FIELD_TAG) == null ? null : String.valueOf(hash.get(FIELD_TAG)));
        record.setPayload(hash.get(FIELD_PAYLOAD) == null ? null : String.valueOf(hash.get(FIELD_PAYLOAD)));
        String status = hash.get(FIELD_STATUS) == null ? null : String.valueOf(hash.get(FIELD_STATUS));
        record.setStatus(status == null ? null : TaskStatus.valueOf(status));
        record.setRetryCount(parseInt(String.valueOf(hash.get(FIELD_RETRY_COUNT)), 0));
        long nextRunTime = parseLong(String.valueOf(hash.get(FIELD_NEXT_RUN_TIME)), 0L);
        record.setNextRunTime(nextRunTime > 0 ? new Timestamp(nextRunTime) : null);
        record.setResult(fromJson(hash.get(FIELD_RESULT)));
        record.setErrorMsg(hash.get(FIELD_ERROR_MSG) == null ? null : String.valueOf(hash.get(FIELD_ERROR_MSG)));
        long createTime = parseLong(String.valueOf(hash.get(FIELD_CREATE_TIME)), 0L);
        long updateTime = parseLong(String.valueOf(hash.get(FIELD_UPDATE_TIME)), 0L);
        record.setCreateTime(createTime > 0 ? new Timestamp(createTime) : null);
        record.setUpdateTime(updateTime > 0 ? new Timestamp(updateTime) : null);
        return record;
    }

    private String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("结果序列化失败:" + e.getMessage(), e);
        }
    }

    private TaskResult fromJson(Object json) {
        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(String.valueOf(json), TaskResult.class);
        } catch (Exception e) {
            logger.warn("结果反序列化失败:{}", e.getMessage());
            return null;
        }
    }

    private int parseInt(String value, int defaultValue) {
        if (value == null || "null".equals(value) || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private long parseLong(String value, long defaultValue) {
        if (value == null || "null".equals(value) || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
