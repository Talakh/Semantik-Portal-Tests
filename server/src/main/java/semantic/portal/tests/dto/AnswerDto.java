package semantic.portal.tests.dto;

import lombok.Data;
import semantic.portal.tests.model.Answer;
import semantic.portal.tests.model.Test;

@Data
public class AnswerDto {
    Test test;
    Answer answer;
}
