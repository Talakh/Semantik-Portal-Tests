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

import java.util.*;
import java.util.stream.Collectors;

import static semantic.portal.tests.enums.ThesesClassEnum.ESSENCE;

@Slf4j
@Service
public class OneCorrectAnswerTestImpl implements SPTest {
    private static final int ANSWERS_COUNT = 4;
    private static final String questionTemplate = "What statement is the most applicable to the concept \"%s\"?";
    private static final List<String> thesesTypesForAnswer = Lists.newArrayList(ESSENCE.getValue());
    private static final Random random = new Random();

    @Override
    public Test create(List<ConceptDto> concepts, List<ThesisDTO> theses) {
        Map<String, List<ConceptDto>> possibleDomainsForTest = filterPossibleDomains(concepts, theses);
        String domainForTest = getDomainForTest(possibleDomainsForTest);
        List<ConceptDto> domainConceptsForTest = possibleDomainsForTest.get(domainForTest);

        ConceptDto question = getRandomConcept(domainConceptsForTest);

        return Test.builder()
                .question(String.format(questionTemplate, question.getConcept()))
                .answers(getAnswers(question, theses, domainConceptsForTest))
                .type(TestTypeEnum.ONE_CORRECT_ANSWER)
                .build();
    }
    private Map<String, List<ConceptDto>> filterPossibleDomains(List<ConceptDto> concepts, List<ThesisDTO> theses) {
        List<Integer> possibleConcepts =  theses.stream()
                .filter(t -> thesesTypesForAnswer.contains(t.getClazz()))
                .map(ThesisDTO::getConceptId)
                .collect(Collectors.toList());

        return concepts.stream()
                .filter(conceptDto -> possibleConcepts.contains(conceptDto.getId()))
                .collect(Collectors.groupingBy(ConceptDto::getDomain))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() >= ANSWERS_COUNT)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private String getDomainForTest(Map<String, List<ConceptDto>> domainsMap) {
        return new ArrayList<>(domainsMap.keySet()).get(random.nextInt(domainsMap.size()));
    }

    private ConceptDto getRandomConcept(List<ConceptDto> concepts) {
        ConceptDto concept = concepts.get(random.nextInt(concepts.size()));
        concepts.remove(concept);
        return concept;
    }

    private List<Answer> getAnswers(ConceptDto question, List<ThesisDTO> theses, List<ConceptDto> concepts) {
        List<Answer> answers = new ArrayList<>();
        answers.add(Answer.createAnswer(getConceptThesis(question.getId(), theses), Boolean.TRUE));
        answers.addAll(generateWrongAnswers(concepts, theses));
        Collections.shuffle(answers);
        return answers;
    }

    private List<Answer> generateWrongAnswers(List<ConceptDto> concepts, List<ThesisDTO> thesis) {
        List<Integer> conceptsIds = concepts.stream().map(ConceptDto::getId).collect(Collectors.toList());
        List<Answer> answers = thesis.stream()
                .filter(t -> conceptsIds.contains(t.getConceptId()))
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
