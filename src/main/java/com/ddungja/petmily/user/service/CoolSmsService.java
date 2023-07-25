package com.ddungja.petmily.user.service;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class CoolSmsService {
    final DefaultMessageService messageService;

    public CoolSmsService(Environment environment) {
        this.messageService = new DefaultMessageService(Objects.requireNonNull(environment.getProperty("coolsms.apiKey")), Objects.requireNonNull(environment.getProperty("coolsms.apiSecretKey")), "https://api.coolsms.co.kr");
    }

    public String sendCertificationNumber(String phoneNumber) {
        Message message = new Message();
        message.setFrom("01063169037");
        message.setTo(phoneNumber);
        StringBuilder certificationNumber = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            certificationNumber.append((int) (Math.random() * 10));
        }
        message.setText("[펫밀리] 본인확인 인증번호[" + certificationNumber + "]입니다. ");
        SingleMessageSentResponse singleMessageSentResponse = messageService.sendOne(new SingleMessageSendingRequest(message));
        log.debug("certificationNumber = {}", certificationNumber);
        log.debug("singleMessageSentResponse = {}", singleMessageSentResponse);
        return certificationNumber.toString();
    }

}
