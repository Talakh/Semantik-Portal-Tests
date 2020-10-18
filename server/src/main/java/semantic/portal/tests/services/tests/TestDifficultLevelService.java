package semantic.portal.tests.services.tests;

import semantic.portal.tests.enums.DifficultLevelEnum;
import semantic.portal.tests.model.TestDifficultLevel;

import java.util.Collection;

public interface TestDifficultLevelService {
    TestDifficultLevel getTestDifficultLevel(DifficultLevelEnum difficultLevelEnum);

    TestDifficultLevel updateTestDifficult(TestDifficultLevel difficultLevel);

    Collection<TestDifficultLevel> getCurrentLevels();
}
