package semantic.portal.tests.services.api;

import semantic.portal.tests.dto.*;

import java.util.List;

public interface BranchApiService {
    BranchDto getAll(String branch);

    List<ThesisDTO> getTheses(String branch);

    List<ConceptDto> getConcepts(String branch);

    List<ThesisDTO> getRelations(String branch);

    List<DidacticDto> getDidactic(String branch);

    List<BranchChildDto> getRootChildren();
}
