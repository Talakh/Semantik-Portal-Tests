package semantic.portal.tests.services.tests.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semantic.portal.tests.services.api.impl.BranchApiServiceImpl;

@SpringBootTest
class OneCorrectAnswerForDemoCodeTestImplTest {
    @Autowired
    private BranchApiServiceImpl branchApiService;
    @Autowired
    private OneCorrectAnswerForDemoCodeTestImpl demoCodeTest;

    @Test
    void name() {
        semantic.portal.tests.model.Test test = demoCodeTest.create(branchApiService.getConcepts("java"),
                branchApiService.getTheses("java"));
        System.out.println(test);
    }
}
