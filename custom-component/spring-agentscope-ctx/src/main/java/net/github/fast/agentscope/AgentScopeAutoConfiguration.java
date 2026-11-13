package net.github.fast.agentscope;

import io.agentscope.core.session.SessionLogStore;
import io.agentscope.core.agui.adapter.AguiAdapterConfig;
import io.agentscope.core.agui.adapter.AguiAgentAdapterFactory;
import io.agentscope.extensions.jdbc.dialect.AbstractJdbcDialect;
import io.agentscope.extensions.jdbc.store.JdbcStore;
import io.agentscope.harness.agent.filesystem.remote.RemoteFilesystem;
import io.agentscope.harness.agent.session.WorkspaceSessionLogStore;
import java.nio.file.Path;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@AutoConfiguration(after = DataSourceAutoConfiguration.class)
@ConditionalOnProperty(prefix = "fast.agentscope", name = "enabled", havingValue = "true", matchIfMissing = true)
public class AgentScopeAutoConfiguration {
    @Bean(destroyMethod = "close") @Lazy @ConditionalOnMissingBean
    public AgentScopeDataSource agentScopeDataSource(Environment environment, ObjectProvider<DataSource> defaults) {
        return new AgentScopeDataSource(environment, defaults);
    }

    @Bean @ConditionalOnMissingBean
    public ConversationRepository agentConversationRepository(AgentScopeDataSource source) {
        return new JdbcConversationRepository(new JdbcTemplate(source.getDataSource()));
    }

    @Bean @ConditionalOnMissingBean(SessionLogStore.class)
    public SessionLogStore agentSessionLogStore(AgentScopeDataSource source,
            @Value("${fast.agentscope.session-log.auto-create-table:true}") boolean autoCreate) {
        DataSource dataSource = source.getDataSource();
        var dialect = AbstractJdbcDialect.from(dataSource).autoCreateTable(autoCreate).build();
        JdbcStore store = JdbcStore.builder(dataSource).dialect(dialect).build();
        return new WorkspaceSessionLogStore(new RemoteFilesystem(store, List.of("agentscope", "session-log")));
    }

    @Bean(destroyMethod = "close") @ConditionalOnMissingBean
    public AgentRegistry agentRegistry(List<HarnessAgentDefinition> definitions, SessionLogStore logStore,
            @Value("${fast.agentscope.workspace:./data/agentscope}") String workspace) {
        return new AgentRegistry(definitions, Path.of(workspace), logStore);
    }
    @Bean @ConditionalOnMissingBean
    public ConversationService conversationService(AgentRegistry registry, ConversationRepository repository) {
        return new ConversationService(registry, repository);
    }
    @Bean @ConditionalOnMissingBean
    public AguiAdapterConfig aguiAdapterConfig() {
        return AguiAdapterConfig.builder().enableReasoning(false).emitStateEvents(false).build();
    }
    @Bean @ConditionalOnMissingBean
    public AguiAgentAdapterFactory aguiAgentAdapterFactory() { return AguiAgentAdapterFactory.defaultFactory(); }
    @Bean @ConditionalOnMissingBean
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @ConditionalOnProperty(prefix = "fast.agentscope", name = "controller-enabled", havingValue = "true", matchIfMissing = true)
    public AgentSessionController agentSessionController(AgentRegistry registry, ConversationService service, UserContextProvider users) {
        return new AgentSessionController(registry, service, users);
    }
    @Bean @ConditionalOnMissingBean
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @ConditionalOnProperty(prefix = "fast.agentscope", name = "agui-enabled", havingValue = "true", matchIfMissing = true)
    public AguiMvcController aguiMvcController(ConversationService service, UserContextProvider users,
            AguiAgentAdapterFactory adapters, AguiAdapterConfig config,
            @Value("${fast.agentscope.agui.sse-timeout:60000}") long timeout) {
        return new AguiMvcController(service, users, adapters, config, timeout);
    }
}
