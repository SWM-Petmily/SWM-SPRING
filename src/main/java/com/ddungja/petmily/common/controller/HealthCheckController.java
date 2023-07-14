package com.ddungja.petmily.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HealthCheckController {
    @GetMapping("/")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok("LoadBalancer Health Check");
    }
}
