package com.ddungja.petmily.user.controller;


import com.ddungja.petmily.user.controller.response.CertificationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Slf4j
public class CoolSmsController {
    final DefaultMessageService messageService;

    public CoolSmsController(Environment environment) {
        this.messageService = new DefaultMessageService(Objects.requireNonNull(environment.getProperty("coolsms.apiKey")), Objects.requireNonNull(environment.getProperty("coolsms.apiSecretKey")), "https://api.coolsms.co.kr");
    }

    
    @Operation(summary = "인증번호 문자 발송")
    @ApiResponse(responseCode = "200", description = "인증번호 문자 발송 성공", content = @Content(schema = @Schema(implementation = CertificationResponse.class)))
    @PostMapping("/send")
    public ResponseEntity<?> sendOne(@RequestBody String phoneNumber) {
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
        return ResponseEntity.ok(CertificationResponse.from(certificationNumber.toString()));
    }


}
