package net.github.fastdev.asynctask.jdbc;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.github.fastdev.asynctask.AsyncTaskProperties;
import net.github.fastdev.asynctask.TaskEngine;
import net.github.fastdev.asynctask.TaskStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * <P><B>JDBC异步任务自动装配:</B></P>
 * fastdev.task.store=jdbc时生效，基于JdbcTemplate，不依赖MyBatis。
 * SERVICE列按服务隔离：queueName未配置时用spring.application.name。
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月20日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Configuration
@ConditionalOnProperty(prefix = "fastdev.task", name = "store", havingValue = "jdbc")
@ConditionalOnClass(NamedParameterJdbcTemplate.class)
@EnableConfigurationProperties(AsyncTaskProperties.class)
public class AsyncTaskJdbcConfig {


    private static final Logger logger = LoggerFactory.getLogger(AsyncTaskJdbcConfig.class);

    @Value("${spring.application.name:}")
    private String applicationName;

    @Bean
    @ConditionalOnMissingBean(NamedParameterJdbcTemplate.class)
    public NamedParameterJdbcTemplate asyncTaskJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public AsyncTaskTableInitializer asyncTaskTableInitializer(DataSource dataSource,
                                                               AsyncTaskProperties properties) {
        resolveNamespace(properties);
        AsyncTaskTableInitializer initializer = new AsyncTaskTableInitializer(dataSource, properties);
        initializer.initialize();
        return initializer;
    }

    @Bean
    @ConditionalOnMissingBean(TaskStore.class)
    public TaskStore jdbcTaskStore(NamedParameterJdbcTemplate jdbcTemplate, ObjectMapper objectMapper,
                                   AsyncTaskProperties properties) {
        return new JdbcTaskStore(jdbcTemplate, objectMapper, properties);
    }

    @Bean
    @ConditionalOnMissingBean(TaskEngine.class)
    public TaskEngine jdbcTaskEngine(TaskStore taskStore, ApplicationContext applicationContext,
                                     AsyncTaskProperties properties, ObjectMapper objectMapper) {
        return new JdbcTaskEngine(taskStore, applicationContext, properties, objectMapper);
    }

    /**
     * 解析服务名：显式queueName > 应用名 > 随机名兜底
     * 多服务共用DB时按SERVICE列隔离，避免互相抢任务
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月20日
     *
     * @author Japson Huang
     */
    private void resolveNamespace(AsyncTaskProperties properties) {
        String queueName = properties.getQueueName();
        if (queueName != null && !queueName.trim().isEmpty()) {
            properties.setNamespace(queueName);
            return;
        }
        if (applicationName != null && !applicationName.trim().isEmpty()) {
            properties.setNamespace(applicationName);
            logger.info("异步任务JDBC按服务隔离，SERVICE={}", applicationName);
            return;
        }
        String fallback = "async-task-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        properties.setNamespace(fallback);
        logger.warn("未配置fastdev.task.queue-name且无spring.application.name，"
                + "使用随机服务名:{}（重启后任务不可恢复，建议显式配置）", fallback);
    }
}
