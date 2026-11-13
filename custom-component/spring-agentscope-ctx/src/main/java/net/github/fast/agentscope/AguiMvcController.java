package net.github.fast.agentscope;

import io.agentscope.core.agui.adapter.AguiAdapterConfig;
import io.agentscope.core.agui.adapter.AguiAgentAdapterFactory;
import io.agentscope.core.agui.encoder.AguiEventEncoder;
import io.agentscope.core.agui.event.AguiEvent;
import io.agentscope.core.agui.model.RunAgentInput;
import io.agentscope.core.agui.runtime.AguiRequestBodyParser;
import io.agentscope.core.util.JsonException;
import java.io.IOException;
import org.reactivestreams.Subscription;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;

/** Servlet MVC transport using the SDK's AG-UI adapter and event encoder, not its Boot 4 starter. */
@RestController
@RequestMapping("${fast.agentscope.agui.path-prefix:${fast.agentscope.path-prefix:/api}/agui}")
public class AguiMvcController {
    private final ConversationService service;
    private final UserContextProvider users;
    private final AguiAgentAdapterFactory adapters;
    private final AguiAdapterConfig config;
    private final long timeout;
    private final AguiEventEncoder encoder = new AguiEventEncoder();
    private final AguiRequestBodyParser parser = new AguiRequestBodyParser();

    public AguiMvcController(ConversationService service, UserContextProvider users,
            AguiAgentAdapterFactory adapters, AguiAdapterConfig config, long timeout) {
        this.service = service; this.users = users; this.adapters = adapters; this.config = config; this.timeout = timeout;
    }

    @PostMapping(value = "/run", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter run(@RequestHeader("X-Agent-Id") String agentId, @RequestBody String body) {
        return execute(agentId, body);
    }

    @PostMapping(value = "/run/{agentId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter runWithAgentId(@PathVariable String agentId, @RequestBody String body) {
        return execute(agentId, body);
    }

    private SseEmitter execute(String agentId, String body) {
        UserContext owner = users.currentUser();
        if (owner == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "缺少用户身份");
        RunAgentInput input;
        try { input = parser.parse(body); }
        catch (JsonException | IllegalArgumentException failure) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AG-UI 请求格式无效", failure);
        }
        if (input.getRunId() == null || input.getRunId().isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "runId 不能为空");
        if (input.hasResume())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AG-UI 直接调用暂不支持 resume");
        // Validate identity, ownership and execution conflicts before committing an SSE response.
        ConversationService.Execution execution = service.beginDirect(agentId, input.getThreadId(), owner);
        SseEmitter emitter = new SseEmitter(timeout);
        BaseSubscriber<AguiEvent> subscriber = new BaseSubscriber<>() {
            @Override protected void hookOnSubscribe(Subscription subscription) { request(1); }
            @Override protected void hookOnNext(AguiEvent event) {
                try {
                    emitter.send(SseEmitter.event().data(encoder.encodeToJson(event), MediaType.APPLICATION_JSON));
                    request(1);
                } catch (IOException | RuntimeException failure) {
                    cancel();
                    emitter.completeWithError(failure);
                }
            }
            @Override protected void hookOnComplete() { emitter.complete(); }
            @Override protected void hookOnError(Throwable failure) {
                try {
                    // Do not leak internal exceptions, prompts or credentials to the client.
                    emitter.send(SseEmitter.event().data(encoder.encodeToJson(new AguiEvent.RunError(
                            input.getThreadId(), input.getRunId(), "智能体执行失败", "AGENT_EXECUTION_FAILED")), MediaType.APPLICATION_JSON));
                    emitter.complete();
                } catch (IOException | RuntimeException sendFailure) { emitter.completeWithError(sendFailure); }
            }
            @Override protected void hookFinally(SignalType signal) { execution.close(); }
        };
        emitter.onCompletion(subscriber::dispose);
        emitter.onTimeout(() -> { subscriber.dispose(); emitter.complete(); });
        emitter.onError(failure -> subscriber.dispose());
        try {
            Flux.defer(() -> adapters.create(execution.agent(), config).run(input, execution.context()))
                    .subscribeOn(Schedulers.boundedElastic()).subscribe(subscriber);
        } catch (RuntimeException | Error failure) {
            subscriber.dispose();
            execution.close();
            throw failure;
        }
        return emitter;
    }
}
