package semantic.portal.tests.services.tests.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import semantic.portal.tests.services.api.impl.BranchApiServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SeveralCorrectAnswersTestImplTest {
    @Autowired
    private SeveralCorrectAnswersTestImpl severalCorrectAnswersTest;
    @Autowired
    private BranchApiServiceImpl branchApiService;

    @Test
    @Repeat(value = 100)
    public void createTest(){
        System.out.println(severalCorrectAnswersTest.create(
                branchApiService.getConcepts("java"), branchApiService.getTheses("java")));
    }
}
