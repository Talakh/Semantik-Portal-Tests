package semantic.portal.tests.services.api.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semantic.portal.tests.services.api.ThesesApiService;

@SpringBootTest
class ThesesApiServiceImplTest {
    @Autowired
    private ThesesApiService thesesApiService;

    @Test
    public void getAll() {
        thesesApiService.getAll();
    }
}
