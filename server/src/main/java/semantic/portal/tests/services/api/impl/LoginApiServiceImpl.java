package semantic.portal.tests.services.api.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import semantic.portal.tests.security.SemanticLoginDto;
import semantic.portal.tests.services.api.LoginApiService;

@Service
@Slf4j
public class LoginApiServiceImpl implements LoginApiService {

    private final WebClient webClient;

    public LoginApiServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public SemanticLoginDto getSemanticLogin(String login, String psw) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/user/check/{login}/{psw}")
                        .build(login, psw))
                .retrieve()
                .bodyToMono(SemanticLoginDto.class)
                .doOnError(e -> log.error("SemanticLoginDto: login: {}, psw: {}, error: {}", login, psw, e.getMessage()))
                .block();
    }
}
