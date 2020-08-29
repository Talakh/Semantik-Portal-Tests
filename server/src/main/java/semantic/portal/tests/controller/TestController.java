package semantic.portal.tests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.services.tests.SimpleTest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/admin/test")
public class TestController {

    @Autowired
    private SimpleTest simpleTest;

    @GetMapping("/get/{branch}/{limit}")
    public List<Test> getTests(@PathVariable String branch, @PathVariable Integer limit) {
        System.out.println("branch" + branch);
        return simpleTest.getTests(branch, limit);
    }

    @PostMapping("/create")
    public void createTest(@RequestBody Map<String, String> request) {
        String branch = request.get("branch");
        simpleTest.createTests(branch);
    }

    @PostMapping("/complete")
    public Boolean completeTest(@RequestBody Map<String, String> answer) {
        String answerId = answer.get("answerId");
        String testId = answer.get("testId");
        return simpleTest.completeTest(UUID.fromString(answerId), UUID.fromString(testId));
    }
}
