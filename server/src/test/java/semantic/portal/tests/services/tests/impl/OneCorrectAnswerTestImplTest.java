package semantic.portal.tests.services.tests.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semantic.portal.tests.services.api.impl.BranchApiServiceImpl;

@SpringBootTest
public class OneCorrectAnswerTestImplTest {

    @Autowired
    private OneCorrectAnswerTestImpl oneCorrectAnswerTest;
    @Autowired
    private BranchApiServiceImpl branchApiService;

    @Test
    public void createTest(){
        System.out.println(oneCorrectAnswerTest.create(
                branchApiService.getConcepts("java"), branchApiService.getTheses("java")));
    }
}
