package com.ddungja.petmily.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberProducer {

    private final Producer producer;
    private static final String EXCHANGE = "petmily.exchange";
    private static final String ROUTE_KEY = "petmily.key";

    public void sendMember(Member member) {
        MemberMessage memberMessage = MemberMessage.builder()
                .memberId(member.getId())
                .build();
        producer.producer(EXCHANGE, ROUTE_KEY, memberMessage);
        log.info("메시지 생성 = {}", memberMessage);
    }
}