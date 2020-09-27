package semantic.portal.tests.services.tests.impl;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import semantic.portal.tests.services.api.impl.BranchApiServiceImpl;

@SpringBootTest
public class OneCorrectAnswerByDefinitionTestImplTest {

    @Autowired
    OneCorrectAnswerByDefinitionTestImpl oneCorrectAnswerByDefinitionTest;
    @Autowired
    private BranchApiServiceImpl branchApiService;

    @RepeatedTest(100)
    public void createTest() {
        System.out.println(oneCorrectAnswerByDefinitionTest.create(
                branchApiService.getConcepts("laravel"), branchApiService.getTheses("laravel")));
    }
}
