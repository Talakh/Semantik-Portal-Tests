package semantic.portal.tests.services.tests;

import semantic.portal.tests.model.Test;

import java.util.List;
import java.util.UUID;

public interface SimpleTest {

    void createTests(String branchName);

    List<Test> getTests(String branchName, Integer limit);

    Boolean completeTest(UUID answerId, UUID testId);
}
