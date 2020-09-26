package semantic.portal.tests.services.api.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semantic.portal.tests.services.api.ConceptApiService;

import java.util.Arrays;

@SpringBootTest
class ConceptApiServiceImplTest {
    @Autowired
    private ConceptApiService conceptApiService;

    @Test
    public void getConceptById() {
        conceptApiService.getConceptById(2177);
    }

    @Test
    public void getThesis() {
        conceptApiService.getThesis(2177);
    }

    @Test
    public void getRelations() {
        conceptApiService.getRelations(2177);
    }

    @Test
    public void getConceptsDidacticBefore() {
        conceptApiService.getConceptsDidacticBefore(2177);
    }

    @Test
    public void getConceptsDidacticAfter() {
        conceptApiService.getConceptsDidacticAfter(2177);
    }
}
