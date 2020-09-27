package semantic.portal.tests.services.tests;

import semantic.portal.tests.dto.AnswerCheckDto;
import semantic.portal.tests.dto.AnswerDto;

public interface CheckService {
    AnswerCheckDto check(AnswerDto answer);
}
