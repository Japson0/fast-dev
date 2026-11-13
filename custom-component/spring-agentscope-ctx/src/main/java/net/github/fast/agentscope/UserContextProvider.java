package net.github.fast.agentscope;

/** Implement in the host application; never obtain identity from untrusted request headers here. */
@FunctionalInterface
public interface UserContextProvider {
    UserContext currentUser();
}
