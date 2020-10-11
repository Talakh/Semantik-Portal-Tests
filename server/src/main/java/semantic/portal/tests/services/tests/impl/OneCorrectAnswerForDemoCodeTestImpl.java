package semantic.portal.tests.services.tests.impl;

import com.google.common.collect.Lists;
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

import static semantic.portal.tests.enums.ThesesClassEnum.DEMO_CODE;
import static semantic.portal.tests.enums.ThesesClassEnum.ESSENCE;

@Service
public class OneCorrectAnswerForDemoCodeTestImpl implements SPTest {
    private static final int ANSWERS_COUNT = 4;
    private static final String QUESTION_TEMPLATE = "The purpose of which concept describes the demo code?";
    private static final List<String> thesesTypesForAnswer = Lists.newArrayList(DEMO_CODE.getValue(),
            ESSENCE.getValue());

    @Override
    public Test create(List<ConceptDto> concepts, List<ThesisDTO> theses) {
        Map<Integer, ConceptDto> possibleConceptsForTest = TestUtils.filterPossibleConcepts(thesesTypesForAnswer ,concepts, theses);
        ThesisDTO demoCodeTheses = getDemoCodeThesisForAnswer(possibleConceptsForTest, theses);
        ConceptDto question = possibleConceptsForTest.get(demoCodeTheses.getConceptId());

        return Test.builder()
                .domainUrl(question.getDomain())
                .domainName(question.getConcept())
                .question(QUESTION_TEMPLATE)
                .codeInQuestion(demoCodeTheses.getThesis())
                .answers(getAnswers(question, possibleConceptsForTest))
                .type(TestTypeEnum.ONE_CORRECT_ANSWER)
                .build();
    }


    private ThesisDTO getDemoCodeThesisForAnswer(Map<Integer, ConceptDto> conceptMap, List<ThesisDTO> theses) {
        List<ThesisDTO> possibleTheses = theses.stream()
                .filter(t -> DEMO_CODE.getValue().equals(t.getClazz()))
                .filter(t -> Objects.isNull(t.getToThesisId()))
                .filter(t -> conceptMap.containsKey(t.getConceptId()))
                .collect(Collectors.toList());

        return possibleTheses.get(new Random().nextInt(possibleTheses.size() - 1));
    }

    private List<Answer> getAnswers(ConceptDto question, Map<Integer, ConceptDto> conceptMap) {
        List<Answer> answers = new ArrayList<>();
        answers.add(Answer.createAnswer(conceptMap.get(question.getId()).getConcept(), Boolean.TRUE));
        conceptMap.remove(question.getId());
        answers.addAll(generateWrongAnswers(conceptMap));
        Collections.shuffle(answers);
        return answers;
    }

    private List<Answer> generateWrongAnswers(Map<Integer, ConceptDto> conceptMap) {
        List<Answer> answers = conceptMap.values().stream()
                .map(ConceptDto::getConcept)
                .map(answer -> Answer.createAnswer(answer, Boolean.FALSE))
                .collect(Collectors.toList());
        Collections.shuffle(answers);

        return answers.subList(0, ANSWERS_COUNT - 1);
    }
}
