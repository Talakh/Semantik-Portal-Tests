package semantic.portal.tests.services.tests.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semantic.portal.tests.dto.*;
import semantic.portal.tests.enums.DifficultLevelEnum;
import semantic.portal.tests.enums.GradeEnum;
import semantic.portal.tests.model.*;
import semantic.portal.tests.repository.AttemptRepository;
import semantic.portal.tests.repository.TestRepository;
import semantic.portal.tests.repository.TestResultRepository;
import semantic.portal.tests.services.api.BranchApiService;
import semantic.portal.tests.services.security.UserService;
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
    private final TestResultRepository testResultRepository;
    private final UserService userService;
    private final TestRepository testRepository;

    public TestManagerImpl(BranchApiService branchApiService,
                           TestGenerator testGenerator
            , AttemptRepository attemptRepository, TestResultRepository testResultRepository, UserService userService, TestRepository testRepository) {
        this.branchApiService = branchApiService;
        this.testGenerator = testGenerator;
        this.attemptRepository = attemptRepository;
        this.testResultRepository = testResultRepository;
        this.userService = userService;
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
        return toTestResultDto(getTestResult(tests, questionsCount, correctAnswersCount, percent, grade));
    }

    @Transactional
    public TestResult getTestResult(List<Test> tests, long questionsCount, long correctAnswersCount, long percent, GradeEnum grade) {
        TestResult result = new TestResult();
        result.setUserId(userService.getCurrentUser().getId());
        result.setQuestionsCount(questionsCount);
        result.setCorrectAnswersCount(correctAnswersCount);
        result.setPercent(percent);
        result.setGrade(grade.name());
        result.setPassed(grade.isPassed());
        result.setTopicsToRepeat(getTopicToRepeat(tests));
        testResultRepository.save(result);
        return result;
    }

    private TestResultDto toTestResultDto(TestResult testResult) {
        return TestResultDto.builder()
                .questionsCount(testResult.getQuestionsCount())
                .userId(testResult.getUserId())
                .correctAnswersCount(testResult.getCorrectAnswersCount())
                .percent(testResult.getPercent())
                .grade(testResult.getGrade())
                .passed(testResult.isPassed())
                .topicsToRepeat(testResult.getTopicsToRepeat().stream().map(topicToRepeat -> TopicToRepeatDto.builder()
                        .domainName(topicToRepeat.getDomainName().stream().map(DomainName::getDomainName).collect(Collectors.toSet()))
                        .domainUrl(topicToRepeat.getDomainUrl().stream().map(DomainUrl::getDomainUrl).collect(Collectors.toSet()))
                        .build()).collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<BranchChildDto> getAllBranches() {
        return branchApiService.getRootChildren().stream()
                .sorted(Comparator.comparing(BranchChildDto::getCaption))
                .collect(Collectors.toList());
    }

    @Transactional
    List<TopicToRepeat> getTopicToRepeat(List<Test> tests) {
        return tests.stream()
                .filter(test -> !test.getAnswerResult())
                .map(test -> TopicToRepeat.builder()
                        .domainUrl(test.getDomainUrl().stream()
                                .map(questionUrl -> DomainUrl.builder().domainUrl(questionUrl).build())
                                .collect(Collectors.toSet()))
                        .domainName(test.getDomainName().stream()
                                .map(questionUrl -> DomainName.builder().domainName(questionUrl).build())
                                .collect(Collectors.toSet()))
                        .build())
                .distinct()
                .collect(Collectors.toList());
    }

}
