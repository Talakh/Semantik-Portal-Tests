package semantic.portal.tests.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semantic.portal.tests.dto.AnswerDto;
import semantic.portal.tests.dto.CreateTestRequest;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.services.tests.TestManager;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/admin/test")
public class TestController {
    private final TestManager testManager;

    public TestController(TestManager testManager) {
        this.testManager = testManager;
    }

    @PostMapping("/create")
    public ResponseEntity<List<Test>> getTests(@RequestBody CreateTestRequest request) {
        log.info("Create test request: {}", request);
        return ResponseEntity.ok(testManager.create(request.getBranch(), request.getDifficultLevel()));
    }

    @PutMapping("/answer")
    public ResponseEntity<String> answer(@RequestBody AnswerDto answerDto) {
        log.info("Answer: {}", answerDto);
        return ResponseEntity.ok("Asdaw");
    }
}
