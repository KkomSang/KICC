package kicc.cicd.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping
    public String healthCheck() {
        return "I'm healthy✅";
    }
}
