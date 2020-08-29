package semantic.portal.tests.services.tests.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import semantic.portal.tests.model.Answer;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.repository.TestRepository;
import semantic.portal.tests.services.api.BranchApiService;
import semantic.portal.tests.services.api.ConceptApiService;
import semantic.portal.tests.services.tests.SimpleTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SimpleTestImpl implements SimpleTest {

    @Autowired
    private BranchApiService branchApiService;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private ConceptApiService conceptApiService;

    @Override
    public void createTests(String branchName) {
        testRepository.saveAll(fillTest(branchName));
    }

    @Override
    public List<Test> getTests(String branchName, Integer limit) {
        return testRepository.findAllByBranch(branchName);
    }

    @Override
    public Boolean completeTest(UUID answerId, UUID testId) {
        Test test = testRepository.findById(testId).orElseThrow(() -> new NullPointerException("Test not found"));
        for (Answer answer : test.getAnswers()) {
            if (answer.getId().equals(answerId) && answer.getIsCorrect().equals(true)) {
                return true;
            }
        }
        return false;
    }

    private List<Test> fillTest(String branchName) {
        List<Test> tests = new ArrayList<>();
        branchApiService.getConcepts(branchName)
                .forEach(conceptDto -> {
                            Test test = new Test();
                            test.setQuestion(conceptDto.getConcept());
                            test.setAnswers(createAnswers(conceptDto.getId()));
                            test.setBranch(branchName);
                            tests.add(test);
                        }
                );
        return tests;
    }

    private List<Answer> createAnswers(Integer conceptId) {
        return conceptApiService.getThesis(conceptId)
                .stream()
                .filter(thesisDTO -> !thesisDTO.getThesis().isEmpty())
                .map(thesisDTO -> Answer.builder()
                        .answer(thesisDTO.getThesis())
                        .isCorrect(true)
                        .build())
                .collect(Collectors.toList());
    }

}
