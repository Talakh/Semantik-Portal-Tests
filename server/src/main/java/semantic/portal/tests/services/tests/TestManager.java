package semantic.portal.tests.services.tests;

import semantic.portal.tests.dto.BranchChildDto;
import semantic.portal.tests.dto.TestResultDto;
import semantic.portal.tests.enums.DifficultLevelEnum;
import semantic.portal.tests.model.Attempt;

import java.util.List;
import java.util.UUID;

public interface TestManager {
    Attempt create(BranchChildDto branch, DifficultLevelEnum difficult);

    TestResultDto getResult(UUID attemptId);

    List<BranchChildDto> getAllBranches();
}
