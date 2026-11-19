package net.github.fast.agentscope;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import static org.assertj.core.api.Assertions.assertThat;

class AgentScopeDataSourceTest {
    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(DataSourceAutoConfiguration.class, AgentScopeAutoConfiguration.class))
            .withPropertyValues("spring.datasource.url=jdbc:h2:mem:business", "spring.datasource.username=sa");

    @Test void defaultsToBusinessDataSource() {
        runner.run(context -> {
            assertThat(context).hasNotFailed();
            assertThat(context.getBean(AgentScopeDataSource.class).getDataSource()).isSameAs(context.getBean(DataSource.class));
            assertThat(context).hasSingleBean(DataSource.class);
        });
    }

    @Test void dedicatedPoolDoesNotReplaceBusinessDataSourceAndIsClosed() {
        HikariDataSource[] pool = new HikariDataSource[1];
        runner.withPropertyValues("fast.agentscope.datasource.url=jdbc:h2:mem:agent",
                "fast.agentscope.datasource.username=sa", "fast.agentscope.datasource.hikari.maximum-pool-size=2")
                .run(context -> {
                    assertThat(context).hasNotFailed();
                    assertThat(context).hasSingleBean(DataSource.class);
                    pool[0] = (HikariDataSource) context.getBean(AgentScopeDataSource.class).getDataSource();
                    assertThat(pool[0]).isNotSameAs(context.getBean(DataSource.class));
                    assertThat(pool[0].getMaximumPoolSize()).isEqualTo(2);
                    new ResourceDatabasePopulator(new ClassPathResource("agentscope/agent-conversation.sql")).execute(pool[0]);
                    Conversation c = new Conversation("test", "demo", 1L, "none", "test", 1L, 1L, false);
                    context.getBean(ConversationRepository.class).create(c);
                    assertThat(new JdbcTemplate(pool[0]).queryForObject("SELECT COUNT(*) FROM agent_conversation", Integer.class)).isEqualTo(1);
                });
        assertThat(pool[0].isClosed()).isTrue();
    }
}
