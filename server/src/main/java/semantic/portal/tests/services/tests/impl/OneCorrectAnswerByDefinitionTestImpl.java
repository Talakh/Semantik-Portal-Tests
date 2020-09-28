package semantic.portal.tests.services.tests.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.model.Answer;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.services.api.ConceptApiService;
import semantic.portal.tests.services.tests.SPTest;

import java.util.*;
import java.util.stream.Collectors;

import static semantic.portal.tests.enums.TestTypeEnum.ONE_CORRECT_ANSWER;
import static semantic.portal.tests.enums.ThesesClassEnum.ESSENCE;

@Service
public class OneCorrectAnswerByDefinitionTestImpl implements SPTest {
    private static final String questionTemplate = "The purpose of which concept describes the statement \"%s\"?";
    private static final int ANSWERS_COUNT = 4;
    private static final List<String> thesesTypesForAnswer = Lists.newArrayList(ESSENCE.getValue());

    private final ConceptApiService conceptApiService;
    private static final Random random = new Random();

    public OneCorrectAnswerByDefinitionTestImpl(ConceptApiService conceptApiService) {
        this.conceptApiService = conceptApiService;
    }

    @Override
    public Test create(List<ConceptDto> concepts, List<ThesisDTO> theses) {
        List<ThesisDTO> thesisDTOS = getRandomTheseByConcept(concepts, theses);
        ThesisDTO thesisDTO = getRandomThesis(thesisDTOS);
        return Test.builder()
                .question(String.format(questionTemplate, thesisDTO.getThesis()))
                .answers(createAnswers(concepts, thesisDTO))
                .type(ONE_CORRECT_ANSWER)
                .build();
    }

    private List<ThesisDTO> getRandomTheseByConcept(List<ConceptDto> concepts, List<ThesisDTO> thesisDTOS) {
        Map<String, List<ConceptDto>> possibleDomainsForTest = filterPossibleDomains(concepts);
        String domainForTest = getDomainForTest(possibleDomainsForTest);
        List<ConceptDto> domainConceptsForTest = possibleDomainsForTest.get(domainForTest);
        List<ThesisDTO> thesisDto = new ArrayList<>();
        for (ConceptDto conceptDto : domainConceptsForTest) {
            thesisDTOS.stream()
                    .filter(thesisDTO -> thesesTypesForAnswer.contains(thesisDTO.getClazz()))
                    .filter(thesisDTO -> conceptDto.getId() == thesisDTO.getConceptId())
                    .forEach(thesisDto::add);
        }
        if (!thesisDto.isEmpty()) {
            return thesisDto;
        } else {
            return getRandomTheseByConcept(concepts, thesisDTOS);
        }
    }

    private ThesisDTO getRandomThesis(List<ThesisDTO> thesisDTOS) {
        ThesisDTO thesis = thesisDTOS.get(random.nextInt(thesisDTOS.size()));
        thesisDTOS.remove(thesis);
        return thesis;
    }

    private List<Answer> createAnswers(List<ConceptDto> concepts, ThesisDTO thesisDTO) {
        ConceptDto rightConceptDto = conceptApiService.getConceptById(thesisDTO.getConceptId());
        List<Answer> answers = new ArrayList<>();
        Answer rightAnswer = Answer.createAnswer(rightConceptDto.getConcept(), true);
        answers.add(rightAnswer);
        List<ConceptDto> conceptDtos =  filterPossibleDomains(concepts).get(rightConceptDto.getDomain());
        Collections.shuffle(conceptDtos);
       conceptDtos.stream()
                .map(conceptDto -> Answer.createAnswer(conceptDto.getConcept(), false))
                .filter(answer -> !Objects.equals(rightAnswer.getAnswer(), answer.getAnswer()))
                .limit(4)
                .forEach(answers::add);
        Collections.shuffle(answers);
        return answers;
    }

    private Map<String, List<ConceptDto>> filterPossibleDomains(List<ConceptDto> concepts) {
        return concepts.stream()
                .collect(Collectors.groupingBy(ConceptDto::getDomain))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() >= ANSWERS_COUNT)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private String getDomainForTest(Map<String, List<ConceptDto>> domainsMap) {
        return new ArrayList<>(domainsMap.keySet()).get(random.nextInt(domainsMap.size()));
    }

}
