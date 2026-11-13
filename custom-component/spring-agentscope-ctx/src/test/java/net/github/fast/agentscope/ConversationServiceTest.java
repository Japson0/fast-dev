package net.github.fast.agentscope;

import io.agentscope.core.model.Model;
import io.agentscope.core.session.SessionLogStore;
import io.agentscope.harness.agent.HarnessAgent;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.server.ResponseStatusException;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConversationServiceTest {
    private ConversationService service;
    private JdbcConversationRepository repository;
    private final UserContext owner = new UserContext(1L, 10L);

    @BeforeEach void setup() {
        var db = new EmbeddedDatabaseBuilder().generateUniqueName(true).setType(EmbeddedDatabaseType.H2).build();
        new ResourceDatabasePopulator(new ClassPathResource("agentscope/agent-conversation.sql")).execute(db);
        repository = new JdbcConversationRepository(new JdbcTemplate(db));
        HarnessAgentDefinition definition = new HarnessAgentDefinition() {
            public String id() { return "demo"; }
            public String name() { return "Demo"; }
            public String description() { return "demo"; }
            protected String modelName() { return "unused"; }
            protected Model model() { return mock(Model.class); }
            public void configure(HarnessAgent.Builder builder) { }
        };
        service = new ConversationService(new AgentRegistry(List.of(definition), Path.of("target/test-workspace"),
                mock(SessionLogStore.class)), repository);
    }
    @Test void creationListingAndOwnership() {
        Conversation c = service.create("demo", "  hello  ", owner);
        assertThat(c.title()).isEqualTo("hello");
        assertThat(service.list("demo", owner, 0, 50)).containsExactly(c);
        assertThat(repository.find("demo", c.sessionId(), new UserContext(2L, 10L))).isEmpty();
        assertThat(repository.find("demo", c.sessionId(), new UserContext(1L, 11L))).isEmpty();
        assertThatThrownBy(() -> service.archive("demo", c.sessionId(), new UserContext(2L, 10L)))
                .isInstanceOf(ResponseStatusException.class);
    }
    @Test void archivePreventsExecution() {
        Conversation c = service.create("demo", null, owner);
        service.archive("demo", c.sessionId(), owner);
        assertThat(service.require("demo", c.sessionId(), owner).archived()).isTrue();
        assertThatThrownBy(() -> service.run("demo", c.sessionId(), owner, "hello"))
                .isInstanceOf(ResponseStatusException.class).hasMessageContaining("会话已归档");
    }
    @Test void invalidPagingRejected() {
        assertThatThrownBy(() -> service.list("demo", owner, -1, 10)).isInstanceOf(ResponseStatusException.class);
    }
}
