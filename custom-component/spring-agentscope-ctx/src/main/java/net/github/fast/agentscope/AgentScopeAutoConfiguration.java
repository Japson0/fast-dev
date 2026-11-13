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
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;

@AutoConfiguration(after = DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(AgentScopeProperties.class)
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
            @Value("${fast.agentscope.workspace:./data/agentscope}") String workspace,
            AgentScopeProperties properties) {
        return new AgentRegistry(definitions, Path.of(workspace), logStore, properties);
    }
    @Bean
    public org.springframework.beans.factory.InitializingBean agentScopeRegisterRelayModel(
            AgentScopeProperties properties, Environment environment) {
        return () -> {
            String baseUrl = environment.getProperty("fast.agentscope.model-base-url",
                    environment.getProperty("agi-chat.url", environment.getProperty("AGI_CHAT_URL")));
            if (baseUrl == null || baseUrl.isBlank()) return;
            properties.setModelBaseUrl(baseUrl);
            io.agentscope.core.model.ModelRegistry.registerFactory("^agi:\\S+$", name -> {
                if (!name.startsWith("agi:")) throw new IllegalArgumentException("Invalid AGI model name: " + name);
                String upstreamModel = name.substring("agi:".length());
                if (upstreamModel.isBlank()) throw new IllegalArgumentException("agi: model name must not be blank");
                io.agentscope.core.model.Model resolved = io.agentscope.extensions.model.openai.OpenAIChatModel.builder()
                        .baseUrl(baseUrl)
                        .apiKey(properties.getModelApiKey() == null ? "" : properties.getModelApiKey())
                        .modelName(upstreamModel).stream(true).build();
                resolved = AgiRelayModel.createDelegate(resolved, name);
                return AgiRelayModel.wrap(resolved, properties);
            });
        };
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
