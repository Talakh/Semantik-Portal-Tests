package semantic.portal.tests.services.tests.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.model.Answer;
import semantic.portal.tests.model.Question;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.services.api.ConceptApiService;
import semantic.portal.tests.services.tests.SPTest;

import java.util.*;
import java.util.stream.Collectors;

import static semantic.portal.tests.enums.ThesesClassEnum.ESSENCE;

@Service
public class MatchTestImpl implements SPTest {
    private static final int ANSWERS_COUNT = 4;

    private static final List<String> thesesTypesForAnswer = Lists.newArrayList(ESSENCE.getValue());
    private static final Random random = new Random();
    private final ConceptApiService conceptApiService;

    @Autowired
    public MatchTestImpl(ConceptApiService conceptApiService) {
        this.conceptApiService = conceptApiService;
    }

    public Test create(List<ConceptDto> concepts, List<ThesisDTO> theses) {
        List<Question> questions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        Map<Integer, ThesisDTO> thesisDTOS = new HashMap<>();
        for(ThesisDTO thesisDTO : getRandomThesis(concepts, theses))
        {
            thesisDTOS.putIfAbsent(thesisDTO.getConceptId(), thesisDTO);
        }
        for (ThesisDTO thesisDTO : thesisDTOS.values()) {
            ConceptDto rightConceptDto = conceptApiService.getConceptById(thesisDTO.getConceptId());
            Answer rightAnswer = Answer.createAnswer(rightConceptDto.getConcept(), true);
            rightAnswer.setId(UUID.randomUUID());
            answers.add(rightAnswer);
            Question question = new Question();
            question.setAnswerId(rightAnswer.getId());
            question.setQuestion(thesisDTO.getThesis());
            questions.add(question);
        }
        Collections.shuffle(questions);
        Collections.shuffle(answers);
        return Test.builder()
                .answers(answers)
                .question(questions)
                .build();
    }

    private List<ThesisDTO> getRandomThesis(List<ConceptDto> concepts, List<ThesisDTO> theses) {
        List<ThesisDTO> filteredTheses = getRandomTheseByConcept(concepts, theses);
        List<ThesisDTO> randomTheses = new ArrayList<>();
        for (int i = 0; i < filteredTheses.size(); i++) {
            ThesisDTO thesisDTO = filteredTheses.get(random.nextInt(filteredTheses.size()));
            randomTheses.add(thesisDTO);
            filteredTheses.remove(thesisDTO);
        }
        return randomTheses;
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
        if (!thesisDto.isEmpty() && thesisDto.size()>=4) {
            return thesisDto;
        } else {
            return getRandomTheseByConcept(concepts, thesisDTOS);
        }
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
