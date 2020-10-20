package semantic.portal.tests.services.tests;

import org.springframework.transaction.annotation.Transactional;
import semantic.portal.tests.model.TestResult;

import java.util.List;
import java.util.UUID;

public interface TestResultService {
    @Transactional
    List<TestResult> getTestResultByUserId(UUID userId);
}
