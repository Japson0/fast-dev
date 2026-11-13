package net.github.fast.agentscope;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

/** Keeps the optional private pool out of Spring's business DataSource candidate selection. */
public final class AgentScopeDataSource implements AutoCloseable {
    private final DataSource dataSource;
    private final boolean owned;

    public AgentScopeDataSource(Environment environment, ObjectProvider<DataSource> defaults) {
        Binder binder = Binder.get(environment);
        DataSourceProperties properties = binder.bind("fast.agentscope.datasource", Bindable.of(DataSourceProperties.class))
                .orElseGet(DataSourceProperties::new);
        owned = properties.getUrl() != null && !properties.getUrl().isBlank();
        if (owned) {
            HikariDataSource pool = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
            try {
                binder.bind("fast.agentscope.datasource.hikari", Bindable.ofInstance(pool));
                dataSource = pool;
            } catch (RuntimeException failure) {
                pool.close();
                throw failure;
            }
        } else {
            dataSource = defaults.getObject();
        }
    }

    public DataSource getDataSource() { return dataSource; }

    @Override public void close() {
        if (owned) ((HikariDataSource) dataSource).close();
    }
}
