package semantic.portal.tests.services.tests;

import semantic.portal.tests.enums.DifficultLevelEnum;
import semantic.portal.tests.model.Test;

import java.util.List;

public interface TestManager {
    List<Test> create(String branchName, DifficultLevelEnum difficult);
}
