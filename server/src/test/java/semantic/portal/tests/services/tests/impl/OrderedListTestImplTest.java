package semantic.portal.tests.services.tests.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semantic.portal.tests.services.api.impl.BranchApiServiceImpl;

@SpringBootTest
class OrderedListTestImplTest {
    @Autowired
    private BranchApiServiceImpl branchApiService;
    @Autowired
    private UnorderedListTestImpl unorderedListTest;

    @Test
    void name() {
        semantic.portal.tests.model.Test test = unorderedListTest.create(branchApiService.getConcepts("java"),
                branchApiService.getTheses("java"));
        System.out.println(test);
    }
}
