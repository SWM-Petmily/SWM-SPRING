package com.ddungja.petmily.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok("LoadBalancer Health Check");
    }
}
