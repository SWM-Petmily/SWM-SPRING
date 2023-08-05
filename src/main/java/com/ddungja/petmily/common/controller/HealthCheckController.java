package com.ddungja.petmily.common.controller;

import io.sentry.Sentry;
import io.sentry.spring.jakarta.tracing.SentrySpan;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @Operation(summary = "LoadBalancer Health Check")
    @GetMapping("/")
    @SentrySpan
    public ResponseEntity<?> health() {
        return ResponseEntity.ok("LoadBalancer Health Check");
    }
}
