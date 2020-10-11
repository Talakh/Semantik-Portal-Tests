package semantic.portal.tests.services.tests.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.enums.TestTypeEnum;
import semantic.portal.tests.model.Answer;
import semantic.portal.tests.model.Question;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.services.api.ConceptApiService;
import semantic.portal.tests.services.tests.SPTest;
import semantic.portal.tests.utils.TestUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static semantic.portal.tests.enums.ThesesClassEnum.ESSENCE;

@Service
public class MatchTestImpl implements SPTest {
    private static final List<String> thesesTypesForAnswer = Lists.newArrayList(ESSENCE.getValue());
    private static final String QUESTION_TEMPLATE = "Match sentences with the statement \"%s\"?";
    private final ConceptApiService conceptApiService;

    @Autowired
    public MatchTestImpl(ConceptApiService conceptApiService) {
        this.conceptApiService = conceptApiService;
    }

    public Test create(List<ConceptDto> concepts, List<ThesisDTO> theses) {
        List<Question> questions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        Map<Integer, ThesisDTO> thesisDTOS = getRandomThesis(theses).stream()
                .collect(Collectors.toMap(ThesisDTO::getConceptId, thesisDTO -> thesisDTO, (a, b) -> a));
        thesisDTOS.values().forEach(thesisDTO -> {
            ConceptDto rightConceptDto = conceptApiService.getConceptById(thesisDTO.getConceptId());
            Answer rightAnswer = Answer.builder().answer(rightConceptDto.getConcept()).build();
            rightAnswer.setMatchId(UUID.randomUUID());
            answers.add(rightAnswer);
            Question question = new Question();
            question.setAnswerId(rightAnswer.getMatchId());
            question.setQuestion(thesisDTO.getThesis());
            questions.add(question);
        });
        Collections.shuffle(questions);
        Collections.shuffle(answers);

        // TODO: 04.10.2020 add domainUrl/domainName/question
        return Test.builder()
                .question(QUESTION_TEMPLATE)
                .matchQuestion(questions)
                .answers(answers)
                .type(TestTypeEnum.MATCH)
                .build();
    }

    private List<ThesisDTO> getRandomThesis(List<ThesisDTO> theses) {
        return IntStream.range(0, theses.size())
                .mapToObj(i -> TestUtils.getRandomThesis(theses))
                .filter(thesisDTO -> thesesTypesForAnswer.contains(thesisDTO.getClazz()))
                .limit(4)
                .collect(Collectors.toList());
    }
}
