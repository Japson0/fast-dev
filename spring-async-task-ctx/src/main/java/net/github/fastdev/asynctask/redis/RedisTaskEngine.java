package net.github.fastdev.asynctask.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.github.fastdev.asynctask.AbstractTaskEngine;
import net.github.fastdev.asynctask.AsyncTaskProperties;
import net.github.fastdev.asynctask.TaskStore;
import org.springframework.context.ApplicationContext;

/**
 * <P><B>Redis任务引擎:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月19日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class RedisTaskEngine extends AbstractTaskEngine {


    public RedisTaskEngine(TaskStore store, ApplicationContext applicationContext,
                           AsyncTaskProperties properties, ObjectMapper objectMapper) {
        super(store, applicationContext, properties, objectMapper);
    }
}
