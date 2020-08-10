package semantic.portal.tests.services.api;

import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.RelationsDto;

import java.util.List;

public interface ConceptApiService {
    ConceptDto getConceptById(int id);

    List<ConceptDto> getConceptsByIds(List<Integer> ids);

    List<RelationsDto> getThesis(int id);

    List<RelationsDto> getRelations(int id);

    List<ConceptDto> getConceptsDidacticBefore(int id);

    List<ConceptDto> getConceptsDidacticAfter(int id);
}
