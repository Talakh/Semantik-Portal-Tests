package semantic.portal.tests.services.tests.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.enums.DifficultLevelEnum;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.services.api.BranchApiService;
import semantic.portal.tests.services.api.ConceptApiService;
import semantic.portal.tests.services.api.ThesesApiService;
import semantic.portal.tests.services.tests.TestGenerator;
import semantic.portal.tests.services.tests.TestManager;

import java.util.List;

@Slf4j
@Service
public class TestManagerImpl implements TestManager {
    private final BranchApiService branchApiService;
    private final ConceptApiService conceptApiService;
    private final ThesesApiService thesesApiService;
    private final TestGenerator testGenerator;

    public TestManagerImpl(BranchApiService branchApiService, ConceptApiService conceptApiService,
                           ThesesApiService thesesApiService, TestGenerator testGenerator) {
        this.branchApiService = branchApiService;
        this.conceptApiService = conceptApiService;
        this.thesesApiService = thesesApiService;
        this.testGenerator = testGenerator;
    }


    @Override
    public List<Test> create(String branchName, DifficultLevelEnum difficult) {
        List<ConceptDto> concepts = branchApiService.getConcepts(branchName);
        List<ThesisDTO> theses = branchApiService.getTheses(branchName);
        return testGenerator.generate(concepts, theses, difficult);
    }
}
