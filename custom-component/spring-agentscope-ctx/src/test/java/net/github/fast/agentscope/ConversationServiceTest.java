package net.github.fast.agentscope;

import io.agentscope.core.model.Model;
import org.springframework.web.server.ResponseStatusException;
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
                mock(SessionLogStore.class), new AgentScopeProperties()), repository);
    }
    @Test void creationListingAndOwnership() {
        Conversation c = service.create("demo", "  hello  ", owner);
        assertThat(c.title()).isEqualTo("hello");
        var page = service.page("demo", owner, new PageRequest<SessionQuery>());
        assertThat(page.getRecords()).containsExactly(c);
        assertThat(page.getTotal()).isEqualTo(1);
        assertThat(page.getSize()).isEqualTo(20);
        var filtered = new PageRequest<SessionQuery>();
        filtered.setCondition(new SessionQuery("hel"));
        assertThat(service.page("demo", owner, filtered).getTotal()).isEqualTo(1);
        filtered.setCondition(new SessionQuery("nope"));
        assertThat(service.page("demo", owner, filtered).getTotal()).isZero();
        assertThat(repository.find("demo", c.sessionId(), new UserContext(2L, 10L))).isEmpty();
        assertThat(repository.find("demo", c.sessionId(), new UserContext(1L, 11L))).isEmpty();
        assertThatThrownBy(() -> service.archive("demo", c.sessionId(), new UserContext(2L, 10L)))
                .isInstanceOf(ResponseStatusException.class);
    }
    @Test void listsOlderConversationFirstAfterNewActivity() {
        var older = repository.create(new Conversation("older", "demo", owner.userId(), owner.tenantKey(),
                "Older", 1L, 1L, false));
        var newer = repository.create(new Conversation("newer", "demo", owner.userId(), owner.tenantKey(),
                "Newer", 2L, 2L, false));
        assertThat(service.page("demo", owner, new PageRequest<SessionQuery>()).getRecords())
                .extracting(Conversation::sessionId).containsExactly(newer.sessionId(), older.sessionId());

        repository.touch(older, 3L);

        assertThat(service.page("demo", owner, new PageRequest<SessionQuery>()).getRecords())
                .extracting(Conversation::sessionId).containsExactly(older.sessionId(), newer.sessionId());
    }
    @Test void archivePreventsExecution() {
        Conversation c = service.create("demo", null, owner);
        service.archive("demo", c.sessionId(), owner);
        assertThat(service.require("demo", c.sessionId(), owner).archived()).isTrue();
        assertThatThrownBy(() -> service.run("demo", c.sessionId(), owner, "hello"))
                .isInstanceOf(ResponseStatusException.class).hasMessageContaining("会话已归档");
    }
    @Test void invalidPagingRejected() {
        var invalid = new PageRequest<SessionQuery>();
        invalid.setPage(-1);
        assertThatThrownBy(() -> service.page("demo", owner, invalid)).isInstanceOf(ResponseStatusException.class);
    }
}
