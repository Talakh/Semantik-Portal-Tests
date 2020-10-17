package semantic.portal.tests.services.tests.impl;

import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semantic.portal.tests.services.api.impl.BranchApiServiceImpl;

@SpringBootTest
class MatchTestImplTest {
    @Autowired
    private MatchTestImpl matchTest;
    @Autowired
    private BranchApiServiceImpl branchApiService;

    @RepeatedTest(50)
    public void createTest(){
//        semantic.portal.tests.model.Test test = matchTest.create(
//                branchApiService.getConcepts("laravel"), branchApiService.getTheses("laravel"));
//        System.out.println("Q - " + test.getMatchQuestion());
//        System.out.println("A - " + test.getAnswers().stream().map(Answer::getAnswer).collect(Collectors.joining("\n")));
    }
}
