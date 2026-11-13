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
    @GetMapping("/agents") public List<AgentRegistry.Descriptor> agents() { return registry.list(); }
    @PostMapping("/agents/{agentCode}/sessions") @ResponseStatus(HttpStatus.CREATED)
    public Conversation create(@PathVariable String agentCode, @RequestBody CreateSession request) {
        return service.create(agentCode, request.title(), user());
    }
    @GetMapping("/agents/{agentCode}/sessions")
    public List<Conversation> list(@PathVariable String agentCode, @RequestParam(defaultValue = "0") int offset,
                                   @RequestParam(defaultValue = "50") int limit) {
        return service.list(agentCode, user(), offset, limit);
    }
    @GetMapping("/agents/{agentCode}/sessions/{id}")
    public ConversationView.Snapshot history(@PathVariable String agentCode, @PathVariable String id,
            @RequestParam(defaultValue = "0") long after, @RequestParam(defaultValue = "200") int limit) {
        return service.history(agentCode, id, user(), after, limit);
    }
    @PostMapping("/agents/{agentCode}/sessions/{id}/archive") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void archive(@PathVariable String agentCode, @PathVariable String id) { service.archive(agentCode, id, user()); }
    @PostMapping("/agents/{agentCode}/sessions/{id}/messages")
    public RunResponse run(@PathVariable String agentCode, @PathVariable String id, @RequestBody RunRequest request) {
        return new RunResponse(service.run(agentCode, id, user(), request.message()));
    }
}
