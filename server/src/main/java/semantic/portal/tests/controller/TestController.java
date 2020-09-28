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
    private final CheckService checkService;

    public TestController(CheckService checkService) {
        this.checkService = checkService;
    }

    @PutMapping("/check")
    public ResponseEntity<AnswerCheckDto> answer(@RequestBody AnswerDto answer) {
        log.info("Answer: {}", answer);
        return ResponseEntity.ok(checkService.check(answer));
    }
}
