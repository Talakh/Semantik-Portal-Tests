package semantic.portal.tests.services.tests.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semantic.portal.tests.model.TestResult;
import semantic.portal.tests.repository.TestResultRepository;
import semantic.portal.tests.services.tests.TestResultService;

import java.util.List;
import java.util.UUID;

@Service
public class TestResultServiceImpl implements TestResultService {

    @Autowired
    TestResultRepository testResultRepository;

    @Override
    public List<TestResult> getTestResultByUserId(UUID userId) {
        return testResultRepository.findByUserId(userId);
    }

}
