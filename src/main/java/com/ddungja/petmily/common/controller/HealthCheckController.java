package com.ddungja.petmily.common.controller;

import io.sentry.spring.jakarta.tracing.SentrySpan;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthCheckController {

    @Operation(summary = "LoadBalancer Health Check")
    @GetMapping("/")
    @SentrySpan
    public ResponseEntity<?> health() {
        log.info("LoadBalancer Health Check");
        return ResponseEntity.ok("LoadBalancer Health Check");
    }
}
