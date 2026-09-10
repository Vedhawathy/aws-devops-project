package com.example.devops;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "AWS DevOps Application is Running!";
    }

    @GetMapping("/health")
    public String health() {
        return "Application is Healthy";
    }
}
