package com.ddungja.petmily.rabbitmq;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open-api")
@RequiredArgsConstructor
@Slf4j
public class HealthOpenApiController {

    private final Producer producer;

    @GetMapping("/health")
    public void health() {
        log.info("health");
    }
}
