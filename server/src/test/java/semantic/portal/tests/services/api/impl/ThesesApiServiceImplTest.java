package semantic.portal.tests.services.api.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.services.api.ThesesApiService;

import java.util.List;
import java.util.stream.Collectors;

import static semantic.portal.tests.enums.ThesesClassEnum.LIST_ITEM;

@SpringBootTest
class ThesesApiServiceImplTest {
    @Autowired
    private ThesesApiService thesesApiService;

    @Test
    public void getAll() {
        List<ThesisDTO> list = thesesApiService.getAll().stream()
                .filter(thesisDTO -> thesisDTO.getClazz().equals(LIST_ITEM.getValue()))
                .collect(Collectors.toList());

        System.out.println(list);
    }
}
