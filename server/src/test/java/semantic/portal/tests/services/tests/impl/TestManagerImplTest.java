package semantic.portal.tests.services.tests.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semantic.portal.tests.enums.DifficultLevelEnum;

@SpringBootTest
class TestManagerImplTest {

    @Autowired
    private TestManagerImpl testManager;

    @Test
    public void test() {
        System.out.println(testManager.create("python", DifficultLevelEnum.LOW));
    }
}
