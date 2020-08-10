package semantic.portal.tests.services.api.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import semantic.portal.tests.dto.BranchDto;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.DidacticDto;
import semantic.portal.tests.dto.RelationsDto;
import semantic.portal.tests.services.api.BranchApiService;

import java.util.List;

@Slf4j
@Service
public class BranchApiServiceImpl implements BranchApiService {
    private final WebClient webClient;

    public BranchApiServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }


    @Override
    public BranchDto getAll(String branch) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/branch/{branch}")
                        .build(branch))
                .retrieve()
                .bodyToMono(BranchDto.class)
                .doOnSuccess(dto -> log.info("getAll: branch: {}, dto: {}", branch, dto))
                .doOnError(e -> log.error("getAll: branch: {}, error: {}", branch, e.getMessage()))
                .block();
    }

    @Override
    public List<RelationsDto> getTheses(String branch) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/branch/{branch}/theses")
                        .build(branch))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<RelationsDto>>() {
                })
                .doOnSuccess(theses -> log.info("getTheses: branch: {}, theses: {}", branch, theses))
                .doOnError(e -> log.error("getTheses: branch: {}, error: {}", branch, e.getMessage()))
                .block();
    }

    @Override
    public List<ConceptDto> getConcepts(String branch) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/branch/{branch}/concepts")
                        .build(branch))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ConceptDto>>() {
                })
                .doOnSuccess(concepts -> log.info("getConcepts: branch: {}, concepts: {}", branch, concepts))
                .doOnError(e -> log.error("getConcepts: branch: {}, error: {}", branch, e.getMessage()))
                .block();
    }

    @Override
    public List<RelationsDto> getRelations(String branch) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/branch/{id}/concepts/relations")
                        .build(branch))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<RelationsDto>>() {
                })
                .doOnSuccess(relations -> log.info("getRelations: branch: {}, relations: {}", branch, relations))
                .doOnError(e -> log.error("getRelations: branch: {}, error: {}", branch, e.getMessage()))
                .block();
    }

    @Override
    public List<DidacticDto> getDidactic(String branch) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/branch/{branch}/concepts/didactic")
                        .build(branch))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<DidacticDto>>() {
                })
                .doOnSuccess(didactic -> log.info("getDidactic: branch: {}, didactic: {}", branch, didactic))
                .doOnError(e -> log.error("getDidactic: branch: {}, error: {}", branch, e.getMessage()))
                .block();
    }
}
