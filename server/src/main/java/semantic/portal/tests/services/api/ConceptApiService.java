package semantic.portal.tests.services.api;

import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;

import java.util.List;
import java.util.Set;

public interface ConceptApiService {
    ConceptDto getConceptById(int id);

    List<ConceptDto> getConceptsByIds(Set<Integer> ids);

    List<ThesisDTO> getThesis(int id);

    List<ThesisDTO> getRelations(int id);

    List<ConceptDto> getConceptsDidacticBefore(int id);

    List<ConceptDto> getConceptsDidacticAfter(int id);
}
