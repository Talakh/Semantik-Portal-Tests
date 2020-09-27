package semantic.portal.tests.services.tests.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.enums.TestTypeEnum;
import semantic.portal.tests.model.Answer;
import semantic.portal.tests.model.Question;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.services.tests.SPTest;

import java.util.*;

import static java.util.stream.Collectors.*;
import static semantic.portal.tests.enums.ThesesClassEnum.ESSENCE;

@Service
public class SeveralCorrectAnswersTestImpl implements SPTest {
    private static final int ANSWERS_COUNT = 4;
    private static final List<String> thesesTypesForAnswer = Lists.newArrayList(ESSENCE.getValue());
    private static final Random random = new Random();

    @Override
    public Test create(List<ConceptDto> concepts, List<ThesisDTO> theses) {
        Map<Integer, List<ThesisDTO>> conceptsWithSeveralAnswers = filterConceptsWithSeveralAnswers(theses);
        Map<String, List<ConceptDto>> possibleDomainsForTest = filterPossibleDomains(concepts, conceptsWithSeveralAnswers.keySet());
        String domainForTest = getDomainForTest(possibleDomainsForTest);
        List<ConceptDto> domainConceptsForTest = possibleDomainsForTest.get(domainForTest);

        ConceptDto question = getRandomConcept(domainConceptsForTest);

        return Test.builder()
                .question(Collections.singletonList(Question.builder().question(question.getConcept()).build()))
                .answers(getAnswers(question, conceptsWithSeveralAnswers, possibleDomainsForTest, theses))
                .type(TestTypeEnum.SEVERAL_CORRECT_ANSWER)
                .build();
    }

    private Map<Integer, List<ThesisDTO>> filterConceptsWithSeveralAnswers(List<ThesisDTO> theses) {
        return theses.stream()
                .filter(t -> thesesTypesForAnswer.contains(t.getClazz()))
                .collect(groupingBy(ThesisDTO::getConceptId))
                .entrySet().stream()
                .filter(e -> !e.getValue().isEmpty() && e.getValue().size() <= ANSWERS_COUNT)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Object filterPossible(List<ConceptDto> concepts,
                                  Set<String> possibleDomains,
                                  List<ThesisDTO> theses) {

        Map<Integer, List<ThesisDTO>> availableThesisForConcept = theses.stream()
                .filter(t -> thesesTypesForAnswer.contains(t.getClazz()))
                .collect(groupingBy(ThesisDTO::getConceptId));

        return concepts.stream()
                .filter(concept -> possibleDomains.contains(concept.getDomain()))
                .collect(groupingBy(ConceptDto::getDomain, mapping(ConceptDto::getId, toList())))
                .entrySet().stream()
                .collect(toMap(Map.Entry::getKey, e -> e
                        .getValue().stream()
                        .map(availableThesisForConcept::get)
                        .flatMap(Collection::stream)
                        .collect(toList())))
                .entrySet().stream()
                .filter(e -> e.getValue().size() >= ANSWERS_COUNT)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<String, List<ConceptDto>> filterPossibleDomains(List<ConceptDto> concepts, Set<Integer> possibleConcepts) {
        return concepts.stream()
                .filter(concept -> possibleConcepts.contains(concept.getId()))
                .collect(groupingBy(ConceptDto::getDomain));
    }

    private String getDomainForTest(Map<String, List<ConceptDto>> domainsMap) {
        return new ArrayList<>(domainsMap.keySet()).get(random.nextInt(domainsMap.size()));
    }

    private ConceptDto getRandomConcept(List<ConceptDto> concepts) {
        ConceptDto concept = concepts.get(random.nextInt(concepts.size()));
        concepts.remove(concept);
        return concept;
    }

    private List<Answer> getAnswers(ConceptDto question,
                                    Map<Integer, List<ThesisDTO>> conceptsWithSeveralAnswers,
                                    Map<String, List<ConceptDto>> possibleDomainsForTest,
                                    List<ThesisDTO> theses) {
        List<Answer> answers = new ArrayList<>();
        conceptsWithSeveralAnswers.get(question.getId())
                .forEach(thesis -> answers.add(Answer.createAnswer(thesis.getThesis(), Boolean.TRUE)));


        answers.addAll(generateWrongAnswers(question, conceptsWithSeveralAnswers, possibleDomainsForTest, theses, ANSWERS_COUNT - answers.size()));
        Collections.shuffle(answers);
        return answers;
    }

    private List<Answer> generateWrongAnswers(ConceptDto question,
                                              Map<Integer, List<ThesisDTO>> conceptsWithSeveralAnswers,
                                              Map<String, List<ConceptDto>> possibleDomainsForTest,
                                              List<ThesisDTO> theses,
                                              int count) {
        List<Answer> answers = possibleDomainsForTest.get(question.getDomain()).stream()
                .map(ConceptDto::getId)
                .filter(id -> id != question.getId())
                .map(conceptsWithSeveralAnswers::get)
                .flatMap(Collection::stream)
                .map(ThesisDTO::getThesis)
                .map(answer -> Answer.createAnswer(answer, Boolean.FALSE))
                .collect(toList());

        if (answers.size() < count) {
            theses.stream()
                    .filter(t -> t.getConceptId() != question.getId())
                    .map(t -> Answer.createAnswer(t.getThesis(), Boolean.FALSE))
                    .limit(count - answers.size())
                    .forEach(answers::add);
            Collections.shuffle(answers);
        } else {
            Collections.shuffle(answers);
            answers = answers.subList(0, count);
        }
        return answers;
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
