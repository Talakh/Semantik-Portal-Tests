package semantic.portal.tests.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semantic.portal.tests.enums.ThesesClassEnum;
import semantic.portal.tests.model.TestDifficultLevel;
import semantic.portal.tests.services.tests.TestDifficultLevelService;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/admin/testDifficultLevels")
public class TestDifficultLevelController {
    private TestDifficultLevelService testDifficultLevelService;

    public TestDifficultLevelController(TestDifficultLevelService testDifficultLevelService) {
        this.testDifficultLevelService = testDifficultLevelService;
    }

    @GetMapping
    public ResponseEntity<Collection<TestDifficultLevel>> getDifficultLevels() {
        return ResponseEntity.ok(testDifficultLevelService.getCurrentLevels());
    }

    @PutMapping
    public ResponseEntity<TestDifficultLevel> updateDifficultLevel(@RequestBody TestDifficultLevel difficultLevel) {
        return ResponseEntity.ok(testDifficultLevelService.updateTestDifficult(difficultLevel));
    }

    @GetMapping("/theseClasses")
    public ResponseEntity<List<ThesesClassEnum>> getTheseClasses() {
        return ResponseEntity.ok(Arrays.asList(ThesesClassEnum.values()));
    }
}
