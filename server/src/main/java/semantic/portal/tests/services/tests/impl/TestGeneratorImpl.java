package semantic.portal.tests.services.tests.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.enums.DifficultLevelEnum;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.services.tests.SPTest;
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

    public TestGeneratorImpl(@Qualifier("oneCorrectAnswerTestImpl") SPTest oneCorrectAnswer,
                             @Qualifier("oneCorrectAnswerByDefinitionTestImpl") SPTest oneCorrectAnswerByDefinition,
                             @Qualifier("oneCorrectAnswerForDemoCodeTestImpl") SPTest oneCorrectAnswerForDemoCode,
                             @Qualifier("severalCorrectAnswersTestImpl") SPTest severalCorrectAnswers,
                             @Qualifier("matchTestImpl") SPTest match) {
        this.severalCorrectAnswers = severalCorrectAnswers;
        this.oneCorrectAnswer = oneCorrectAnswer;
        this.oneCorrectAnswerByDefinition = oneCorrectAnswerByDefinition;
        this.oneCorrectAnswerForDemoCode = oneCorrectAnswerForDemoCode;
        this.match = match;
    }

    @Override
    public List<Test> generate(List<ConceptDto> concepts, List<ThesisDTO> theses, DifficultLevelEnum difficult) {
        List<Test> tests = new ArrayList<>();
        for (int i = 0; i < difficult.getOneAnswerCount(); i++) {
            tests.add(oneCorrectAnswer.create(concepts, theses));
        }
        for (int i = 0; i < difficult.getOneAnswerByDefinitionCount(); i++) {
            tests.add(oneCorrectAnswerByDefinition.create(concepts, theses));
        }
        for (int i = 0; i < difficult.getDemoCodeCount(); i++) {
            tests.add(oneCorrectAnswerForDemoCode.create(concepts, theses));
        }
        for (int i = 0; i < difficult.getSeveralAnswerCount(); i++) {
            tests.add(severalCorrectAnswers.create(concepts, theses));
        }
        for (int i = 0; i < difficult.getMatchCount(); i++) {
            tests.add(match.create(concepts, theses));
        }
        return tests;
    }
}
