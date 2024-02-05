package com.ddungja.petmily.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberConsumer {

    @RabbitListener(queues = "petmily.queue")
    public void sendMember(MemberMessage memberMessage) {
        log.info("memberMessage = {}", memberMessage);
    }
}