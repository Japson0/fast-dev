package net.github.fastdev.asynctask.jdbc;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.github.fastdev.asynctask.AbstractTaskEngine;
import net.github.fastdev.asynctask.AsyncTaskProperties;
import net.github.fastdev.asynctask.TaskStore;
import org.springframework.context.ApplicationContext;

/**
 * <P><B>JDBC任务引擎:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月19日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class JdbcTaskEngine extends AbstractTaskEngine {


    public JdbcTaskEngine(TaskStore store, ApplicationContext applicationContext,
                          AsyncTaskProperties properties, ObjectMapper objectMapper) {
        super(store, applicationContext, properties, objectMapper);
    }
}
