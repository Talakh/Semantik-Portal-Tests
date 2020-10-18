package semantic.portal.tests.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import semantic.portal.tests.exception.CantCreateTestException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CantCreateTestException.class)
    public ResponseEntity<Object> handleCantCreateTestException(CantCreateTestException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Don't enough theses for creating test");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
