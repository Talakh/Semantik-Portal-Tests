package semantic.portal.tests.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semantic.portal.tests.dto.CreateTestRequest;
import semantic.portal.tests.dto.TestResultDto;
import semantic.portal.tests.model.Attempt;
import semantic.portal.tests.model.TestResult;
import semantic.portal.tests.services.tests.TestManager;

import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/admin/attempts")
public class AttemptController {
    private final TestManager testManager;

    public AttemptController(TestManager testManager) {
        this.testManager = testManager;
    }

    @PostMapping
    public ResponseEntity<Attempt> getAttempt(@RequestBody CreateTestRequest request) {
        log.info("Create attempt request: {}", request);
        Attempt attempt = testManager.create(request.getBranch(), request.getDifficultLevel());
        return ResponseEntity.ok(attempt);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestResultDto> getResult(@PathVariable UUID id) {
        return ResponseEntity.ok(testManager.getResult(id));
    }
}
