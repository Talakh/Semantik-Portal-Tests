package semantic.portal.tests.services.tests.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import semantic.portal.tests.dto.BranchChildDto;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.TestResultDto;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.enums.DifficultLevelEnum;
import semantic.portal.tests.model.Attempt;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.repository.AttemptRepository;
import semantic.portal.tests.services.api.BranchApiService;
import semantic.portal.tests.services.tests.TestGenerator;
import semantic.portal.tests.services.tests.TestManager;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TestManagerImpl implements TestManager {
    private final BranchApiService branchApiService;
    private final TestGenerator testGenerator;
    private final AttemptRepository attemptRepository;


    public TestManagerImpl(BranchApiService branchApiService,
                           TestGenerator testGenerator,
                           AttemptRepository attemptRepository) {
        this.branchApiService = branchApiService;
        this.testGenerator = testGenerator;
        this.attemptRepository = attemptRepository;
    }


    @Override
    public Attempt create(String branchName, DifficultLevelEnum difficult) {
        List<ConceptDto> concepts = branchApiService.getConcepts(branchName);
        List<ThesisDTO> theses = branchApiService.getTheses(branchName);
        List<Test> tests = testGenerator.generate(concepts, theses, difficult);
        return attemptRepository.save(Attempt.builder().tests(tests).build());
    }

    @Override
    public TestResultDto finishTest(String testId) {
        return null;
    }

    @Override
    public List<BranchChildDto> getAllBranches() {
        return branchApiService.getRootChildren().stream()
                .sorted(Comparator.comparing(BranchChildDto::getCaption))
                .collect(Collectors.toList());
    }


}
