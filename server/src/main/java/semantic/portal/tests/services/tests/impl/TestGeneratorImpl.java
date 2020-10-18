package semantic.portal.tests.services.tests.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.enums.DifficultLevelEnum;
import semantic.portal.tests.exception.CantCreateTestException;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.model.TestDifficultLevel;
import semantic.portal.tests.services.tests.SPTest;
import semantic.portal.tests.services.tests.TestDifficultLevelService;
import semantic.portal.tests.services.tests.TestGenerator;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TestGeneratorImpl implements TestGenerator {
    private final SPTest oneCorrectAnswer;
    private final SPTest oneCorrectAnswerByDefinition;
    private final SPTest oneCorrectAnswerForDemoCode;
    private final SPTest severalCorrectAnswers;
    private final SPTest match;
    private final SPTest unorderedList;
    private final TestDifficultLevelService testDifficultLevelService;

    public TestGeneratorImpl(@Qualifier("oneCorrectAnswerTestImpl") SPTest oneCorrectAnswer,
                             @Qualifier("oneCorrectAnswerByDefinitionTestImpl") SPTest oneCorrectAnswerByDefinition,
                             @Qualifier("oneCorrectAnswerForDemoCodeTestImpl") SPTest oneCorrectAnswerForDemoCode,
                             @Qualifier("severalCorrectAnswersTestImpl") SPTest severalCorrectAnswers,
                             @Qualifier("matchTestImpl") SPTest match,
                             @Qualifier("unorderedListTestImpl") SPTest unorderedList,
                             TestDifficultLevelService testDifficultLevelService) {
        this.severalCorrectAnswers = severalCorrectAnswers;
        this.oneCorrectAnswer = oneCorrectAnswer;
        this.oneCorrectAnswerByDefinition = oneCorrectAnswerByDefinition;
        this.oneCorrectAnswerForDemoCode = oneCorrectAnswerForDemoCode;
        this.match = match;
        this.unorderedList = unorderedList;
        this.testDifficultLevelService = testDifficultLevelService;
    }

    @Override
    public List<Test> generate(List<ConceptDto> concepts, List<ThesisDTO> theses, DifficultLevelEnum difficult) {

        TestDifficultLevel difficultLevel = testDifficultLevelService.getTestDifficultLevel(difficult);
        if (isThesesEnoughForTestBuild(concepts, theses, difficultLevel)) {
            return generateTests(concepts, theses, difficultLevel);
        } else {
            throw new CantCreateTestException();
        }
    }

    private List<Test> generateTests(List<ConceptDto> concepts, List<ThesisDTO> theses, TestDifficultLevel difficultLevel) {
        List<Test> tests = new ArrayList<>();
        for (int i = 0; i < difficultLevel.getOneAnswerCount(); i++) {
            tests.add(oneCorrectAnswer.create(concepts, theses, difficultLevel.getOneAnswerThesisTypes()));
        }
        for (int i = 0; i < difficultLevel.getOneAnswerByDefinitionCount(); i++) {
            tests.add(oneCorrectAnswerByDefinition.create(concepts, theses,
                    difficultLevel.getOneAnswerByDefinitionThesisTypes()));
        }
        for (int i = 0; i < difficultLevel.getDemoCodeCount(); i++) {
            tests.add(oneCorrectAnswerForDemoCode.create(concepts, theses,
                    difficultLevel.getDemoCodeThesisTypes()));
        }
        for (int i = 0; i < difficultLevel.getSeveralAnswerCount(); i++) {
            tests.add(severalCorrectAnswers.create(concepts, theses, difficultLevel.getSeveralAnswerThesisTypes()));
        }
        for (int i = 0; i < difficultLevel.getMatchCount(); i++) {
            tests.add(match.create(concepts, theses, difficultLevel.getMatchThesisTypes()));
        }
        for (int i = 0; i < difficultLevel.getUnorderedListCount(); i++) {
            tests.add(unorderedList.create(concepts, theses, difficultLevel.getUnorderedListThesisTypes()));
        }
        return tests;
    }

    private boolean isThesesEnoughForTestBuild(List<ConceptDto> concepts,
                                               List<ThesisDTO> theses,
                                               TestDifficultLevel level) {
        return oneCorrectAnswer.isEnoughTheses(concepts, theses, level) &&
                oneCorrectAnswerByDefinition.isEnoughTheses(concepts, theses, level) &&
                severalCorrectAnswers.isEnoughTheses(concepts, theses, level) &&
                match.isEnoughTheses(concepts, theses, level) &&
                oneCorrectAnswerForDemoCode.isEnoughTheses(concepts, theses, level) &&
                unorderedList.isEnoughTheses(concepts, theses, level);
    }
}
