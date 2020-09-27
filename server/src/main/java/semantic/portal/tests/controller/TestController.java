package semantic.portal.tests.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semantic.portal.tests.dto.AnswerCheckDto;
import semantic.portal.tests.dto.AnswerDto;
import semantic.portal.tests.dto.CreateTestRequest;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.services.tests.CheckService;
import semantic.portal.tests.services.tests.TestManager;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/admin/test")
public class TestController {
    private final TestManager testManager;
    private final CheckService checkService;

    public TestController(TestManager testManager, CheckService checkService) {
        this.testManager = testManager;
        this.checkService = checkService;
    }

    @PostMapping("/create")
    public ResponseEntity<List<Test>> getTests(@RequestBody CreateTestRequest request) {
        log.info("Create test request: {}", request);
        List<Test> tests = testManager.create(request.getBranch(), request.getDifficultLevel());
        return ResponseEntity.ok(tests);
    }

    @PutMapping("/check")
    public ResponseEntity<AnswerCheckDto> answer(@RequestBody AnswerDto answer) {
        log.info("Answer: {}", answer);
        return ResponseEntity.ok(checkService.check(answer));
    }
}
