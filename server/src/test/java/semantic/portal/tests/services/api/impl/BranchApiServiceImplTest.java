package semantic.portal.tests.services.api.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semantic.portal.tests.services.api.BranchApiService;

@SpringBootTest
class BranchApiServiceImplTest {
    @Autowired
    private BranchApiService branchApiService;
    private String BRANCH = "java";

    @Test
    public void getAll() {
        System.out.println(branchApiService.getAll(BRANCH));
    }

    @Test
    public void getTheses() {
        System.out.println(branchApiService.getTheses(BRANCH));
    }

    @Test
    public void getConcepts() {
        System.out.println(branchApiService.getConcepts(BRANCH));
    }

    @Test
    public void getRelations() {
        System.out.println(branchApiService.getRelations(BRANCH));
    }

    @Test
    public void getDidactic() {
        System.out.println(branchApiService.getDidactic(BRANCH));
    }
}
