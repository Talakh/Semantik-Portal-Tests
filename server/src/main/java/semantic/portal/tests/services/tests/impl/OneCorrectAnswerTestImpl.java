package semantic.portal.tests.services.tests.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.enums.TestTypeEnum;
import semantic.portal.tests.model.Answer;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.services.tests.SPTest;
import semantic.portal.tests.utils.TestUtils;

import java.util.*;
import java.util.stream.Collectors;

import static semantic.portal.tests.enums.ThesesClassEnum.ESSENCE;
import static semantic.portal.tests.utils.TestUtils.getRandomConcept;

@Slf4j
@Service
public class OneCorrectAnswerTestImpl implements SPTest {
    private static final int ANSWERS_COUNT = 4;
    private static final String QUESTION_TEMPLATE = "What statement is the most applicable to the concept \"%s\"?";
    private static final List<String> thesesTypesForAnswer = Lists.newArrayList(ESSENCE.getValue());

    @Override
    public Test create(List<ConceptDto> concepts, List<ThesisDTO> theses) {
        Map<Integer, ConceptDto> possibleConceptsForTest = TestUtils.filterPossibleConcepts(thesesTypesForAnswer,concepts, theses);
        ConceptDto question = getRandomConcept(possibleConceptsForTest);

        return Test.builder()
                .domainUrl(question.getDomain())
                .domainName(question.getConcept())
                .question(String.format(QUESTION_TEMPLATE, question.getConcept()))
                .answers(getAnswers(question, theses, possibleConceptsForTest))
                .type(TestTypeEnum.ONE_CORRECT_ANSWER)
                .build();
    }

    private List<Answer> getAnswers(ConceptDto question, List<ThesisDTO> theses, Map<Integer, ConceptDto> conceptMap) {
        List<Answer> answers = new ArrayList<>();
        answers.add(Answer.createAnswer(getConceptThesis(question.getId(), theses), Boolean.TRUE));
        answers.addAll(generateWrongAnswers(theses, conceptMap.keySet()));
        Collections.shuffle(answers);
        return answers;
    }

    private List<Answer> generateWrongAnswers(List<ThesisDTO> thesis, Set<Integer> conceptIds) {
        List<Answer> answers = thesis.stream()
                .filter(t -> conceptIds.contains(t.getConceptId()))
                .filter(t -> thesesTypesForAnswer.contains(t.getClazz()))
                .map(ThesisDTO::getThesis)
                .map(answer -> Answer.createAnswer(answer, Boolean.FALSE))
                .collect(Collectors.toList());
        Collections.shuffle(answers);

        return answers.subList(0, ANSWERS_COUNT - 1);
    }

    private String getConceptThesis(int conceptId, List<ThesisDTO> theses) {
        return theses.stream()
                .filter(thesis -> thesis.getConceptId() == conceptId)
                .filter(thesis -> thesesTypesForAnswer.contains(thesis.getClazz()))
                .map(ThesisDTO::getThesis)
                .findAny()
                .orElseThrow(NullPointerException::new);
    }

}
