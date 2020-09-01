package semantic.portal.tests.services.api.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.services.api.ConceptApiService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ConceptApiServiceImpl implements ConceptApiService {
    private final WebClient webClient;

    public ConceptApiServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public ConceptDto getConceptById(int id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/concept/{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(ConceptDto.class)
                .doOnError(e -> log.error("getConceptById: id: {}, error: {}", id, e.getMessage()))
                .block();
    }

    @Override
    public List<ConceptDto> getConceptsByIds(Set<Integer> ids) {
        String idsParameter = ids.stream().map(String::valueOf).collect(Collectors.joining(","));

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/concept/{ids}")
                        .build(idsParameter))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ConceptDto>>() {
                })
                .doOnError(e -> log.error("getConceptByIds: ids: {}, error: {}", ids, e.getMessage()))
                .block();
    }

    @Override
    public List<ThesisDTO> getThesis(int id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/concept/{id}/thesis")
                        .build(id))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ThesisDTO>>() {
                })
                .doOnError(e -> log.error("getThesis: id: {}, error: {}", id, e.getMessage()))
                .block();
    }

    @Override
    public List<ThesisDTO> getRelations(int id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/concept/{id}/relations")
                        .build(id))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ThesisDTO>>() {
                })
                .doOnError(e -> log.error("getRelations: id: {}, error: {}", id, e.getMessage()))
                .block();
    }

    @Override
    public List<ConceptDto> getConceptsDidacticBefore(int id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/concept/{id}/didactic/before")
                        .build(id))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ConceptDto>>() {
                })
                .doOnError(e -> log.error("getConceptsDidacticBefore: id: {}, error: {}", id,e.getMessage()))
                .block();
    }

    @Override
    public List<ConceptDto> getConceptsDidacticAfter(int id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/concept/{id}/didactic/after")
                        .build(id))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ConceptDto>>() {
                })
                .doOnError(e -> log.error("getConceptsDidacticAfter: id: {}, error: {}", id, e.getMessage()))
                .block();
    }
}
