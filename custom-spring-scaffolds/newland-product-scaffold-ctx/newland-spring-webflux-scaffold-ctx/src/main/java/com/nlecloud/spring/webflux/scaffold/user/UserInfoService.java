package com.nlecloud.spring.webflux.scaffold.user;

import com.nlecloud.spring.annotation.UserInfo;
import com.nlecloud.spring.annotation.UserInfoImpl;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年06月01日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserInfoService {

    private static final String BASE_URL = "http://nlecloud-upms-user-center-server.upms:19192";

    private final WebClient webClient;

    public UserInfoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public Mono<UserInfoImpl> getUserDetailById(Long id) {
        return webClient.get()
                .uri("/api/user/detail/{id}", id)
                .retrieve()
                .bodyToMono(UserInfoImpl.class);
    }
}
