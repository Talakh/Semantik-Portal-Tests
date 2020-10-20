package semantic.portal.tests.services.tests;

import semantic.portal.tests.enums.DifficultLevelEnum;
import semantic.portal.tests.model.TestDifficultLevel;

import java.util.Collection;
import java.util.List;

public interface TestDifficultLevelService {
    TestDifficultLevel getTestDifficultLevel(DifficultLevelEnum difficultLevelEnum);

    Collection<TestDifficultLevel> updateTestDifficult(List<TestDifficultLevel> difficultLevel);

    Collection<TestDifficultLevel> getCurrentLevels();
}
