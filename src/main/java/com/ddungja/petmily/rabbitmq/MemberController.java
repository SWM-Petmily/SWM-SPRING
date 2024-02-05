package com.ddungja.petmily.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberProducer producer;


    @PostMapping("/member")
    public ResponseEntity<?> message() {
        producer.sendMember(Member.builder()
                .id(1L)
                .name("test")
                .build());
        return ResponseEntity.ok().build();
    }
}
