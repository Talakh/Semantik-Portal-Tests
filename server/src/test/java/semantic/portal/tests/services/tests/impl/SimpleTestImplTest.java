package semantic.portal.tests.services.tests.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semantic.portal.tests.services.tests.SimpleTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SimpleTestImplTest {

    @Autowired
    private SimpleTest simpleTest;

    @Test
    public void test() {
        simpleTest.createTests("java");
    }

}
