package semantic.portal.tests.services.api.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.RelationsDto;
import semantic.portal.tests.services.api.ConceptApiService;

import java.util.List;
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
                .doOnSuccess(dto -> log.info("getConceptById: id: {}, dto: {}", id, dto))
                .doOnError(e -> log.error("getConceptById: id: {}, error: {}", id, e.getMessage()))
                .block();
    }

    @Override
    public List<ConceptDto> getConceptsByIds(List<Integer> ids) {
        String idsParameter = ids.stream().map(String::valueOf).collect(Collectors.joining(","));

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/concept/{ids}")
                        .build(idsParameter))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ConceptDto>>() {
                })
                .doOnSuccess(concepts -> log.info("getConceptByIds: ids: {}, concepts: {}", ids, concepts))
                .doOnError(e -> log.error("getConceptByIds: ids: {}, error: {}", ids, e.getMessage()))
                .block();
    }

    @Override
    public List<RelationsDto> getThesis(int id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/concept/{id}/thesis")
                        .build(id))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<RelationsDto>>() {
                })
                .doOnSuccess(thesis -> log.info("getThesis: id: {}, thesis: {}", id, thesis))
                .doOnError(e -> log.error("getThesis: id: {}, error: {}", id, e.getMessage()))
                .block();
    }

    @Override
    public List<RelationsDto> getRelations(int id) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/concept/{id}/relations")
                        .build(id))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<RelationsDto>>() {
                })
                .doOnSuccess(relations -> log.info("getRelations: id: {}, relations: {}", id, relations))
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
                .doOnSuccess(concepts -> log.info("getConceptsDidacticBefore: id: {}, concepts: {}", id, concepts))
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
                .doOnSuccess(concepts -> log.info("getConceptsDidacticAfter: id: {}, concepts: {}", id, concepts))
                .doOnError(e -> log.error("getConceptsDidacticAfter: id: {}, error: {}", id, e.getMessage()))
                .block();
    }
}
