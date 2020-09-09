package semantic.portal.tests.services.tests.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semantic.portal.tests.model.Answer;
import semantic.portal.tests.services.api.impl.BranchApiServiceImpl;

import java.util.stream.Collectors;

@SpringBootTest
public class OneCorrectAnswerTestImplTest {

    @Autowired
    private OneCorrectAnswerTestImpl oneCorrectAnswerTest;
    @Autowired
    private BranchApiServiceImpl branchApiService;

    @Test
    public void createTest(){
        semantic.portal.tests.model.Test test = oneCorrectAnswerTest.create(
                branchApiService.getConcepts("angular"), branchApiService.getTheses("angular"));
        System.out.println("Q - " + test.getQuestion());
        System.out.println("A - " + test.getAnswers().stream().map(Answer::getAnswer).collect(Collectors.joining("\n")));
    }
}
