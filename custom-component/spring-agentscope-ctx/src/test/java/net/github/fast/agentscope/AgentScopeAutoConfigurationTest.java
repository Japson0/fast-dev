package net.github.fast.agentscope;

import io.agentscope.core.session.SessionLogStore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class AgentScopeAutoConfigurationTest {
    private final WebApplicationContextRunner runner = new WebApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(AgentScopeAutoConfiguration.class))
            .withBean(ConversationRepository.class, () -> mock(ConversationRepository.class))
            .withBean(SessionLogStore.class, () -> mock(SessionLogStore.class))
            .withBean(UserContextProvider.class, () -> () -> new UserContext(1L, 2L));

    @Test void createsControllerAndServices() {
        runner.run(context -> {
            assertThat(context).hasNotFailed();
            assertThat(context).hasSingleBean(AgentSessionController.class);
            assertThat(context).hasSingleBean(AguiMvcController.class);
            assertThat(context).hasSingleBean(ConversationService.class);
            assertThat(context).hasSingleBean(AgentRegistry.class);
        });
    }
    @Test void controllerCanBeDisabled() {
        runner.withPropertyValues("fast.agentscope.controller-enabled=false").run(context -> {
            assertThat(context).hasNotFailed();
            assertThat(context).doesNotHaveBean(AgentSessionController.class);
            assertThat(context).hasSingleBean(AguiMvcController.class);
        });
    }
    @Test void registersRelayModelFactoryWhenConfigured() {
        runner.withPropertyValues("agi-chat.url=http://localhost:8080/openai")
                .run(context -> {
                    assertThat(context).hasNotFailed();
                    assertThat(context).hasBean("agentScopeRegisterRelayModel");
        });
    }
    @Test void readsRelayUrlFromEnvironmentVariableByDefault() {
        runner.withPropertyValues("AGI_CHAT_URL=http://localhost:8080/openai")
                .run(context -> {
                    assertThat(context).hasNotFailed();
                    assertThat(context).hasBean("agentScopeRegisterRelayModel");
                });
    }
    @Test void registryFactoryResolvesAndWrapsOnlyAgiModels() {
        runner.withPropertyValues("agi-chat.url=http://localhost:8080/openai").run(context -> {
            assertThat(context).hasNotFailed();
            io.agentscope.core.model.Model agi = io.agentscope.core.model.ModelRegistry.resolve("agi:chat-model");
            assertThat(agi).isInstanceOf(AgiRelayModel.class);
            assertThat(agi.getModelName()).isEqualTo("agi:chat-model");
            io.agentscope.core.model.Model custom = mock(io.agentscope.core.model.Model.class);
            org.mockito.Mockito.when(custom.getModelName()).thenReturn("custom-model");
            assertThat(AgiRelayModel.wrap(custom, new AgentScopeProperties())).isSameAs(custom);
        });
    }
}
