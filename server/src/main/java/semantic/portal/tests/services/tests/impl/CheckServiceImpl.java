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
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CheckServiceImpl implements CheckService {
    private final TestRepository testRepository;

    public CheckServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    @Transactional
    public AnswerCheckDto check(AnswerDto answer) {
        Test test = testRepository.findById(UUID.fromString(answer.getTestId()))
                .orElseThrow(TestNotFountException::new);
        switch (test.getType()) {
            case ONE_CORRECT_ANSWER:
                return checkOneCorrectAnswer(test, answer.getAnswerId());
            case SEVERAL_CORRECT_ANSWER:
                return checkSeveralCorrectAnswer(test, answer.getAnswerIds());
            case MATCH:
                return checkMatchTest(test, answer);
        }
        throw new EntityNotFoundException("Test with type " + test.getType() + " doesn't exist");
    }

    private AnswerCheckDto checkOneCorrectAnswer(Test test, UUID answerId) {
        Answer correctAnswer = test.getAnswers().stream()
                .filter(Answer::isCorrect)
                .findFirst()
                .orElseThrow(AnswerNotFoundException::new);
        AnswerCheckDto answer = AnswerCheckDto.builder()
                .correctId(correctAnswer.getId())
                .correct(correctAnswer.getId().equals(answerId))
                .build();

        test.setUserAnswerId(answerId);
        test.setAnswerResult(answer.isCorrect());
        testRepository.save(test);

        return answer;
    }

    private AnswerCheckDto checkMatchTest(Test test, AnswerDto answerDto) {
        Map<UUID, UUID> question2Answer = answerDto.getQuestion2Answer();
        Map<UUID, UUID> rightAnswer = test.getMatchQuestion()
                .stream()
                .collect(Collectors.toMap(Question::getId, Question::getAnswerId));
        test.setAnswerResult(question2Answer.equals(rightAnswer));
        test.setUserAnswers(answerDto.getQuestion2Answer());
        testRepository.save(test);

        return AnswerCheckDto.builder().correctAnswerMap(rightAnswer).build();
    }

    private AnswerCheckDto checkSeveralCorrectAnswer(Test test, List<UUID> answerIds) {
        List<UUID> correctAnswerIds = test.getAnswers().stream()
                .filter(Answer::isCorrect)
                .map(Answer::getId)
                .collect(Collectors.toList());
        AnswerCheckDto answer = AnswerCheckDto.builder()
                .correctIds(correctAnswerIds)
                .correct(CollectionUtils.isEqualCollection(correctAnswerIds, answerIds))
                .build();

        test.setUserAnswerIds(answerIds);
        test.setAnswerResult(answer.isCorrect());
        testRepository.save(test);

        return answer;
    }

}
