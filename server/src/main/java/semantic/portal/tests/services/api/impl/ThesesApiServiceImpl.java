package semantic.portal.tests.services.api.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.services.api.ThesesApiService;

import java.util.List;

@Slf4j
@Service
public class ThesesApiServiceImpl implements ThesesApiService {
    private final WebClient webClient;

    public ThesesApiServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<ThesisDTO> getAll() {
        return webClient
                .get()
                .uri("/thesis")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ThesisDTO>>() {})
                .doOnSuccess(theses -> log.info("getAll: thesis: {}", theses))
                .doOnError(e -> log.error("getAll: error: {}", e.getMessage()))
                .block();
    }
}
