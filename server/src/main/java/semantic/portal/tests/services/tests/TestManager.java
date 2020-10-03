package semantic.portal.tests.services.tests;

import semantic.portal.tests.dto.BranchChildDto;
import semantic.portal.tests.dto.TestResultDto;
import semantic.portal.tests.enums.DifficultLevelEnum;
import semantic.portal.tests.model.Attempt;

import java.util.List;

public interface TestManager {
    Attempt create(String branchName, DifficultLevelEnum difficult);

    TestResultDto finishTest(String attemptId);

    List<BranchChildDto> getAllBranches();
}
