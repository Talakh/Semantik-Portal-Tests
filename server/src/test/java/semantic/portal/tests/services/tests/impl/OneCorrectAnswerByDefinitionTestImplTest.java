package semantic.portal.tests.services.tests.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semantic.portal.tests.services.api.impl.BranchApiServiceImpl;

@SpringBootTest
public class OneCorrectAnswerByDefinitionTestImplTest {

    @Autowired
    OneCorrectAnswerByDefinitionTestImpl oneCorrectAnswerByDefinitionTest;
    @Autowired
    private BranchApiServiceImpl branchApiService;

    @Test
    public void createTest() {
        System.out.println(oneCorrectAnswerByDefinitionTest.create(
                branchApiService.getConcepts("angular"), branchApiService.getTheses("angular")));
    }
}
