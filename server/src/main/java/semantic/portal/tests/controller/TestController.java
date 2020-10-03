package semantic.portal.tests.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semantic.portal.tests.dto.AnswerCheckDto;
import semantic.portal.tests.dto.AnswerDto;
import semantic.portal.tests.dto.BranchChildDto;
import semantic.portal.tests.services.api.BranchApiService;
import semantic.portal.tests.services.tests.CheckService;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/admin/test")
public class TestController {
    private final CheckService checkService;
    private final BranchApiService branchApiService;

    public TestController(CheckService checkService, BranchApiService branchApiService) {
        this.checkService = checkService;
        this.branchApiService = branchApiService;
    }

    @PutMapping("/check")
    public ResponseEntity<AnswerCheckDto> answer(@RequestBody AnswerDto answer) {
        log.info("Answer: {}", answer);
        return ResponseEntity.ok(checkService.check(answer));
    }

    @GetMapping("/branches")
    public ResponseEntity<List<BranchChildDto>> getBranches() {
        return ResponseEntity.ok(branchApiService.getRootChildren());
    }
}
