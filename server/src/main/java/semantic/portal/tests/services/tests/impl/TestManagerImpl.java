package semantic.portal.tests.services.tests.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semantic.portal.tests.dto.BranchChildDto;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.TestResultDto;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.enums.DifficultLevelEnum;
import semantic.portal.tests.enums.GradeEnum;
import semantic.portal.tests.model.Attempt;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.repository.AttemptRepository;
import semantic.portal.tests.repository.TestRepository;
import semantic.portal.tests.services.api.BranchApiService;
import semantic.portal.tests.services.tests.TestGenerator;
import semantic.portal.tests.services.tests.TestManager;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TestManagerImpl implements TestManager {
    private final BranchApiService branchApiService;
    private final TestGenerator testGenerator;
    private final AttemptRepository attemptRepository;
    private final TestRepository testRepository;


    public TestManagerImpl(BranchApiService branchApiService,
                           TestGenerator testGenerator,
                           AttemptRepository attemptRepository, TestRepository testRepository) {
        this.branchApiService = branchApiService;
        this.testGenerator = testGenerator;
        this.attemptRepository = attemptRepository;
        this.testRepository = testRepository;
    }


    @Override
    public Attempt create(BranchChildDto branch, DifficultLevelEnum difficult) {
        List<ConceptDto> concepts = branchApiService.getConcepts(branch.getView());
        List<ThesisDTO> theses = branchApiService.getTheses(branch.getView());
        List<Test> tests = testGenerator.generate(concepts, theses, difficult);
        return attemptRepository.save(Attempt.builder()
                .tests(tests)
                .branch(branch.getCaption())
                .build());
    }

    @Transactional
    @Override
    public TestResultDto getResult(UUID attemptId) {
        List<Test> tests = testRepository.findAllByAttemptId(attemptId);
        long questionsCount = tests.size();
        long correctAnswersCount = tests.stream().filter(Test::getAnswerResult).count();
        long percent = Math.round((double) correctAnswersCount / (double) questionsCount * 100);
        GradeEnum grade = GradeEnum.getByValue(percent);

        TestResultDto resultDto = new TestResultDto();
        resultDto.setQuestionsCount(questionsCount);
        resultDto.setCorrectAnswersCount(correctAnswersCount);
        resultDto.setPercent(percent);
        resultDto.setGrade(grade.name());
        resultDto.setPassed(grade.isPassed());
        resultDto.setTopicsToRepeat(getTopicToRepeat(tests));
        return resultDto;
    }

    @Override
    public List<BranchChildDto> getAllBranches() {
        return branchApiService.getRootChildren().stream()
                .sorted(Comparator.comparing(BranchChildDto::getCaption))
                .collect(Collectors.toList());
    }

    private List<TestResultDto.TopicToRepeat> getTopicToRepeat(List<Test> tests) {
        return tests.stream()
                .filter(test -> !test.getAnswerResult())
                .map(test -> TestResultDto.TopicToRepeat.builder()
                        .domainUrl(test.getDomainUrl())
                        .domainName(test.getDomainName())
                        .build())
                .distinct()
                .collect(Collectors.toList());
    }

}
