package net.github.fast.agentscope;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("${fast.agentscope.path-prefix:/api}")
public class AgentSessionController {
    public record CreateSession(String title) { }
    public record RunRequest(String message) { }
    public record RunResponse(String message) { }
    private final AgentRegistry registry;
    private final ConversationService service;
    private final UserContextProvider users;

    public AgentSessionController(AgentRegistry registry, ConversationService service, UserContextProvider users) {
        this.registry = registry; this.service = service; this.users = users;
    }
    private UserContext user() {
        UserContext owner = users.currentUser();
        if (owner == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "缺少用户身份");
        return owner;
    }
    @GetMapping("/agents") public RestResponse<List<AgentRegistry.Descriptor>> agents() {
        return RestResponse.renderSuccess(registry.list());
    }
    @PostMapping("/agents/{agentCode}/sessions") @ResponseStatus(HttpStatus.CREATED)
    public RestResponse<Conversation> create(@PathVariable String agentCode, @RequestBody CreateSession request) {
        return RestResponse.renderSuccess(service.create(agentCode, request.title(), user()));
    }
    @PostMapping("/agents/{agentCode}/sessions/list")
    public RestResponse<PageRequest<Conversation>> list(@PathVariable String agentCode,
            @RequestBody PageRequest<SessionQuery> request) {
        return RestResponse.renderSuccess(service.page(agentCode, user(), request));
    }
    @GetMapping("/agents/{agentCode}/sessions/{id}")
    public RestResponse<ConversationView.Snapshot> history(@PathVariable String agentCode, @PathVariable String id,
            @RequestParam(defaultValue = "0") long after, @RequestParam(defaultValue = "200") int limit) {
        return RestResponse.renderSuccess(service.history(agentCode, id, user(), after, limit));
    }
}
