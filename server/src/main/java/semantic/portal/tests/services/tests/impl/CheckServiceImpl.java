package semantic.portal.tests.services.tests.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import semantic.portal.tests.dto.AnswerCheckDto;
import semantic.portal.tests.dto.AnswerDto;
import semantic.portal.tests.exception.AnswerNotFoundException;
import semantic.portal.tests.exception.TestNotFountException;
import semantic.portal.tests.model.Answer;
import semantic.portal.tests.model.Question;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.repository.TestRepository;
import semantic.portal.tests.services.tests.CheckService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CheckServiceImpl implements CheckService {
    private final TestRepository testRepository;

    public CheckServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public AnswerCheckDto check(AnswerDto answer) {
        Test test = testRepository.findById(UUID.fromString(answer.getTestId()))
                .orElseThrow(TestNotFountException::new);
        switch (test.getType()) {
            case ONE_CORRECT_ANSWER:
                return checkOneCorrectAnswer(test, answer.getAnswerId());
            case SEVERAL_CORRECT_ANSWER:
                return checkSeveralCorrectAnswer(test, answer.getAnswerIds());
            case MATCH:
                return checkMatchTest(test, answer.getAnswerIds());
        }
        throw new EntityNotFoundException("Test with type " + test.getType()  + " doesn't exist");
    }

    private AnswerCheckDto checkOneCorrectAnswer(Test test, UUID answerId) {
        Answer correctAnswer = test.getAnswers().stream()
                .filter(Answer::isCorrect)
                .findFirst()
                .orElseThrow(AnswerNotFoundException::new);
        return AnswerCheckDto.builder()
                .correctId(correctAnswer.getId())
                .isTrue(correctAnswer.getId().equals(answerId))
                .build();
    }

    private AnswerCheckDto checkSeveralCorrectAnswer(Test test, List<UUID> answerIds) {
        List<UUID> correctAnswerIds = test.getAnswers().stream()
                .filter(Answer::isCorrect)
                .map(Answer::getId)
                .collect(Collectors.toList());
        return AnswerCheckDto.builder()
                .correctIds(correctAnswerIds)
                .isTrue(CollectionUtils.isEqualCollection(correctAnswerIds, answerIds))
                .build();
    }


    private AnswerCheckDto checkMatchTest(Test test , List<UUID> answerIds)
    {
        List<UUID> correctAnswerIds = test.getQuestion().stream()
                .map(Question::getAnswerId)
                .collect(Collectors.toList());
        if (CollectionUtils.isEqualCollection(correctAnswerIds, answerIds)) {
            return AnswerCheckDto.builder()
                    .isTrue(Boolean.TRUE)
                    .build();
        } else {
            return AnswerCheckDto.builder()
                    .isTrue(Boolean.FALSE)
                    .correctIds(correctAnswerIds)
                    .build();
        }
    }
}
