package semantic.portal.tests.services.tests.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.enums.TestTypeEnum;
import semantic.portal.tests.model.Answer;
import semantic.portal.tests.model.Question;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.model.TestDifficultLevel;
import semantic.portal.tests.services.api.ConceptApiService;
import semantic.portal.tests.services.tests.SPTest;
import semantic.portal.tests.utils.TestUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MatchTestImpl implements SPTest {
    private static final String QUESTION_TEMPLATE = "Match sentences with the statement.";
    private final ConceptApiService conceptApiService;

    @Autowired
    public MatchTestImpl(ConceptApiService conceptApiService) {
        this.conceptApiService = conceptApiService;
    }

    public Test create(List<ConceptDto> concepts,
                       List<ThesisDTO> theses,
                       List<String> thesesTypesForAnswer) {
        Set<String> questionNames = new HashSet<>();
        Set<String> questionUrl = new HashSet<>();
        List<Question> questions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        Map<Integer, ThesisDTO> thesisDTOS = getRandomThesis(theses, thesesTypesForAnswer).stream()
                .collect(Collectors.toMap(ThesisDTO::getConceptId, thesisDTO -> thesisDTO, (a, b) -> a));
        thesisDTOS.values().forEach(thesisDTO -> {
            ConceptDto rightConceptDto = conceptApiService.getConceptById(thesisDTO.getConceptId());
            Answer rightAnswer = Answer.builder().answer(rightConceptDto.getConcept()).build();
            rightAnswer.setMatchId(UUID.randomUUID());
            answers.add(rightAnswer);
            Question question = new Question();
            questionNames.add(rightConceptDto.getConcept());
            questionUrl.add(rightConceptDto.getDomain());
            question.setAnswerId(rightAnswer.getMatchId());
            question.setQuestion(thesisDTO.getThesis());
            questions.add(question);
        });
        Collections.shuffle(questions);
        Collections.shuffle(answers);

        return Test.builder()
                .domainName(questionNames)
                .domainUrl(questionUrl)
                .question(QUESTION_TEMPLATE)
                .matchQuestion(questions)
                .answers(answers)
                .type(TestTypeEnum.MATCH)
                .build();
    }

    @Override
    public boolean isEnoughTheses(List<ConceptDto> concepts,
                                  List<ThesisDTO> theses,
                                  TestDifficultLevel level) {
        if (level.getMatchCount() <= 0) {
            return true;
        } else {
            return theses.stream()
                    .filter(thesisDTO -> level.getMatchThesisTypes().contains(thesisDTO.getClazz()))
                    .count() >= 4;
        }
    }

    private List<ThesisDTO> getRandomThesis(List<ThesisDTO> theses, List<String> thesesTypesForAnswer) {
        return IntStream.range(0, theses.size())
                .mapToObj(i -> TestUtils.getRandomThesis(theses))
                .filter(thesisDTO -> thesesTypesForAnswer.contains(thesisDTO.getClazz()))
                .limit(4)
                .collect(Collectors.toList());
    }
}
