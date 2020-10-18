package semantic.portal.tests.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class AnswerDto {
    String testId;
    UUID answerId;
    List<UUID> answerIds;
    Map<UUID, UUID> question2Answer;
}
