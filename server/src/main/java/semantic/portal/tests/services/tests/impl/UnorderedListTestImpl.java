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
import java.util.stream.Stream;

import static semantic.portal.tests.enums.ThesesClassEnum.LIST_ITEM;

@Service
public class UnorderedListTestImpl implements SPTest {
    private static final int LIST_GROUPS = 3;
    private static final String QUESTION_TEMPLATE = "Which statements is the most applicable to the concept \"%s\"?";
    private static final List<String> thesesTypesForAnswer = Lists.newArrayList(LIST_ITEM.getValue());

    @Override
    public Test create(List<ConceptDto> concepts, List<ThesisDTO> theses) {
        Map<Integer, ConceptDto> possibleConceptsForTest = filterPossibleConcepts(concepts, theses);
        Map<Integer, List<ThesisDTO>> demoCodeTheses = getDemoCodeThesisForAnswer(possibleConceptsForTest, theses);
        Map<Integer, List<ThesisDTO>> thesesForTest = getThesesMapForTest(demoCodeTheses);

        Integer conceptId = TestUtils.getRandomKey(thesesForTest);
        ConceptDto question = possibleConceptsForTest.get(conceptId);

        return Test.builder()
                .domainUrl(Collections.singleton(question.getDomain()))
                .domainName(Collections.singleton(question.getConcept()))
                .question(String.format(QUESTION_TEMPLATE, question.getConcept()))
                .answers(getAnswers(question, thesesForTest))
                .type(TestTypeEnum.SEVERAL_CORRECT_ANSWER)
                .build();
    }

    private Map<Integer, ConceptDto> filterPossibleConcepts(List<ConceptDto> concepts, List<ThesisDTO> theses) {
        List<Integer> possibleConcepts = theses.stream()
                .filter(t -> thesesTypesForAnswer.contains(t.getClazz()))
                .filter(t -> Objects.isNull(t.getToThesisId()))
                .map(ThesisDTO::getConceptId)
                .collect(Collectors.toList());

        return concepts.stream()
                .filter(conceptDto -> conceptDto.getIsAspect() == 0)
                .filter(conceptDto -> possibleConcepts.contains(conceptDto.getId()))
                .collect(Collectors.toMap(ConceptDto::getId, c -> c));
    }

    private Map<Integer, List<ThesisDTO>> getDemoCodeThesisForAnswer(Map<Integer, ConceptDto> conceptMap,
                                                                     List<ThesisDTO> theses) {
        return theses.stream()
                .filter(t -> thesesTypesForAnswer.contains(t.getClazz()))
                .filter(t -> Objects.isNull(t.getToThesisId()))
                .filter(t -> conceptMap.containsKey(t.getConceptId()))
                .collect(Collectors.groupingBy(ThesisDTO::getConceptId));
    }

    private Map<Integer, List<ThesisDTO>> getThesesMapForTest(Map<Integer, List<ThesisDTO>> thesisMap) {
        Map<Integer, List<ThesisDTO>> map = new HashMap<>();
        for (int i = 0; i < LIST_GROUPS; i++) {
            Integer key = TestUtils.getRandomKey(thesisMap);
            map.put(key, thesisMap.remove(key));
        }
        return map;
    }

    private List<Answer> getAnswers(ConceptDto question, Map<Integer, List<ThesisDTO>> thesesForTest) {
        Stream<Answer> correctAnswerStream = thesesForTest.remove(question.getId()).stream()
                .map(t -> Answer.builder().answer(t.getThesis()).correct(Boolean.TRUE).build());
        Stream<Answer> incorrectAnswerStream = thesesForTest.values().stream()
                .flatMap(Collection::stream)
                .map(t -> Answer.builder().answer(t.getThesis()).correct(Boolean.FALSE).build());
        List<Answer> answers = Stream.concat(correctAnswerStream, incorrectAnswerStream).collect(Collectors.toList());
        Collections.shuffle(answers);
        return answers;
    }
}
