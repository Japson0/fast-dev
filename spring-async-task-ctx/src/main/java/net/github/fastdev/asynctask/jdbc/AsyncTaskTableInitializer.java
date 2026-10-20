package net.github.fastdev.asynctask.jdbc;

import net.github.fastdev.asynctask.AsyncTaskProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <P><B>异步任务表初始化:</B></P>
 * 表不存在时自动建表，fastdev.task.auto-create-table=true开启。
 * RevisionTrail:(Date/Author/Description)
 * 2026年10月20日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class AsyncTaskTableInitializer {


    private static final Logger logger = LoggerFactory.getLogger(AsyncTaskTableInitializer.class);

    private static final String TABLE_NAME = "async_task_record";

    private static final String SCRIPT_PATH = "sql/async_task_record.sql";

    private final DataSource dataSource;

    private final AsyncTaskProperties properties;

    public AsyncTaskTableInitializer(DataSource dataSource, AsyncTaskProperties properties) {
        this.dataSource = dataSource;
        this.properties = properties;
    }

    /**
     * 初始化，表存在则跳过
     * RevisionTrail:(Date/Author/Description)
     * 2026年10月20日
     *
     * @author Japson Huang
     */
    public void initialize() {
        if (!properties.isAutoCreateTable()) {
            return;
        }
        try (Connection connection = DataSourceUtils.getConnection(dataSource)) {
            if (tableExists(connection)) {
                logger.info("异步任务表{}已存在，跳过建表", TABLE_NAME);
                return;
            }
            String script = loadScript();
            executeScript(connection, script);
            logger.info("异步任务表{}创建完成", TABLE_NAME);
        } catch (SQLException e) {
            throw new IllegalStateException("异步任务表初始化失败:" + e.getMessage(), e);
        }
    }

    private boolean tableExists(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet tables = metaData.getTables(connection.getCatalog(), null, TABLE_NAME, new String[]{"TABLE"})) {
            if (tables.next()) {
                return true;
            }
        }
        // 兼容lower_case_table_names等大小写场景
        try (ResultSet tables = metaData.getTables(connection.getCatalog(), null, TABLE_NAME.toUpperCase(), new String[]{"TABLE"})) {
            return tables.next();
        }
    }

    private String loadScript() {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(SCRIPT_PATH);
        if (stream == null) {
            stream = AsyncTaskTableInitializer.class.getClassLoader().getResourceAsStream(SCRIPT_PATH);
        }
        if (stream == null) {
            throw new IllegalStateException("建表脚本不存在:" + SCRIPT_PATH);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new IllegalStateException("建表脚本读取失败:" + SCRIPT_PATH, e);
        }
    }

    private void executeScript(Connection connection, String script) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            for (String sql : script.split(";\\s*\n")) {
                String executable = Arrays.stream(sql.split("\\R"))
                        .filter(l -> !l.trim().startsWith("--"))
                        .collect(Collectors.joining("\n")).trim();
                if (executable.isEmpty()) {
                    continue;
                }
                statement.execute(executable);
            }
        }
    }
}
