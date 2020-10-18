package semantic.portal.tests.services.tests;

import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.model.TestDifficultLevel;

import java.util.List;

public interface SPTest {

    Test create(List<ConceptDto> concepts, List<ThesisDTO> theses, List<String> thesesTypesForAnswer);

    boolean isEnoughTheses(List<ConceptDto> concepts, List<ThesisDTO> theses, TestDifficultLevel level);
}
