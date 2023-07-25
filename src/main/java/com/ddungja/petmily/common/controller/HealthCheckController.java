package com.ddungja.petmily.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @Operation(summary = "LoadBalancer Health Check")
    @GetMapping("/")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok("LoadBalancer Health Check");
    }
}
