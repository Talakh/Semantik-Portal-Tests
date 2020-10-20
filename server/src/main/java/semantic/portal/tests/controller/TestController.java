package semantic.portal.tests.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semantic.portal.tests.dto.AnswerCheckDto;
import semantic.portal.tests.dto.AnswerDto;
import semantic.portal.tests.dto.BranchChildDto;
import semantic.portal.tests.model.TestResult;
import semantic.portal.tests.services.security.UserService;
import semantic.portal.tests.services.tests.CheckService;
import semantic.portal.tests.services.tests.TestManager;
import semantic.portal.tests.services.tests.TestResultService;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/admin/test")
public class TestController {
    private final CheckService checkService;
    private final TestManager testManager;
    private final UserService userService;
    private final TestResultService testResultService;

    public TestController(CheckService checkService, TestManager testManager, UserService userService, TestResultService testResultService) {
        this.checkService = checkService;
        this.testManager = testManager;
        this.userService = userService;
        this.testResultService = testResultService;
    }

    @PutMapping("/check")
    public ResponseEntity<AnswerCheckDto> answer(@RequestBody AnswerDto answer) {
        log.info("Answer: {}", answer);
        AnswerCheckDto answerDto = checkService.check(answer);
        log.info("AnswerDto {}", answerDto);
        return ResponseEntity.ok(answerDto);
    }

    @GetMapping("/branches")
    public ResponseEntity<List<BranchChildDto>> getBranches() {
        return ResponseEntity.ok(testManager.getAllBranches());
    }

    @GetMapping("/currentUserAttempts")
    public List<TestResult> getAttemptByCurrentUser() {
        return testResultService.getTestResultByUserId(userService.getCurrentUser().getId());
    }

}
