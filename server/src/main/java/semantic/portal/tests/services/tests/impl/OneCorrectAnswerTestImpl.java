package semantic.portal.tests.services.tests.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.enums.TestTypeEnum;
import semantic.portal.tests.model.Answer;
import semantic.portal.tests.model.DomainUrl;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.model.TestDifficultLevel;
import semantic.portal.tests.services.tests.SPTest;

import java.util.*;
import java.util.stream.Collectors;

import static semantic.portal.tests.utils.TestUtils.filterPossibleConcepts;
import static semantic.portal.tests.utils.TestUtils.getRandomConcept;

@Slf4j
@Service
public class OneCorrectAnswerTestImpl implements SPTest {
    private static final int ANSWERS_COUNT = 4;
    private static final String QUESTION_TEMPLATE = "What statement is the most applicable to the concept \"%s\"?";

    @Override
    public Test create(List<ConceptDto> concepts,
                       List<ThesisDTO> theses,
                       List<String> thesesTypesForAnswer) {
        Map<Integer, ConceptDto> possibleConceptsForTest = filterPossibleConcepts(thesesTypesForAnswer,concepts, theses);
        ConceptDto question = getRandomConcept(possibleConceptsForTest);

        return Test.builder()
                .domainUrl(Collections.singleton(question.getDomain()))
                .domainName(Collections.singleton(question.getConcept()))
                .question(String.format(QUESTION_TEMPLATE, question.getConcept()))
                .answers(getAnswers(question, theses, possibleConceptsForTest, thesesTypesForAnswer))
                .type(TestTypeEnum.ONE_CORRECT_ANSWER)
                .build();
    }

    @Override
    public boolean isEnoughTheses(List<ConceptDto> concepts,
                                  List<ThesisDTO> theses,
                                  TestDifficultLevel level) {
        if (level.getOneAnswerCount() <= 0) {
            return true;
        } else {
            Map<Integer, ConceptDto> possibleConceptsForTest =
                    filterPossibleConcepts(level.getOneAnswerThesisTypes(), concepts, theses);
            return possibleConceptsForTest.size() >= 4;
        }
    }

    private List<Answer> getAnswers(ConceptDto question,
                                    List<ThesisDTO> theses,
                                    Map<Integer, ConceptDto> conceptMap,
                                    List<String> thesesTypesForAnswer) {
        List<Answer> answers = new ArrayList<>();
        answers.add(Answer.createAnswer(getConceptThesis(question.getId(), theses, thesesTypesForAnswer), Boolean.TRUE));
        answers.addAll(generateWrongAnswers(theses, conceptMap.keySet(), thesesTypesForAnswer));
        Collections.shuffle(answers);
        return answers;
    }

    private List<Answer> generateWrongAnswers(List<ThesisDTO> thesis,
                                              Set<Integer> conceptIds,
                                              List<String> thesesTypesForAnswer) {
        List<Answer> answers = thesis.stream()
                .filter(t -> conceptIds.contains(t.getConceptId()))
                .filter(t -> thesesTypesForAnswer.contains(t.getClazz()))
                .map(ThesisDTO::getThesis)
                .map(answer -> Answer.createAnswer(answer, Boolean.FALSE))
                .collect(Collectors.toList());
        Collections.shuffle(answers);

        return answers.subList(0, ANSWERS_COUNT - 1);
    }

    private String getConceptThesis(int conceptId, List<ThesisDTO> theses, List<String> thesesTypesForAnswer) {
        return theses.stream()
                .filter(thesis -> thesis.getConceptId() == conceptId)
                .filter(thesis -> thesesTypesForAnswer.contains(thesis.getClazz()))
                .map(ThesisDTO::getThesis)
                .findAny()
                .orElseThrow(NullPointerException::new);
    }

}
