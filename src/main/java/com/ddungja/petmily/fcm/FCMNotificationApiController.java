package com.ddungja.petmily.fcm;

import com.ddungja.petmily.fcm.request.FCMNotificationRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Notification", description = "FCM Notification 관련 api 입니다.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notification")
public class FCMNotificationApiController {

    private final FCMNotificationService fcmNotificationService;

    @Operation(summary = "알림 보내기")
    @PostMapping()
    public String sendNotificationByToken(@RequestBody FCMNotificationRequest requestDto) {
        return fcmNotificationService.sendNotificationByToken(requestDto);
    }
}