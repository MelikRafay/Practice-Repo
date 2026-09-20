package com.melikrafay.practicerepo;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @GetMapping("/api/calculate")
    public ResponseEntity<Map<String, Object>> calculate(
            @RequestParam double a,
            @RequestParam double b,
            @RequestParam String op) {

        double result;
        switch (op) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b == 0) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(Map.of("error", "Division by zero is not allowed"));
                }
                result = a / b;
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Unsupported operation"));
        }

        return ResponseEntity.ok(Map.of("result", result));
    }
}
