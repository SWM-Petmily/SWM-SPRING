package com.ddungja.petmily.fcm;

import com.ddungja.petmily.fcm.request.FCMNotificationRequest;
import com.google.firebase.messaging.FirebaseMessagingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Notification", description = "FCM Notification 관련 api 입니다.")
@RequiredArgsConstructor
@RestController
public class FcmNotificationController {

    private final FCMNotificationService fcmNotificationService;

    @Operation(summary = "알림 보내기")
    @ApiResponse(responseCode = "204", description = "알림 보내기 성공")
    @PostMapping("/notification")
    public ResponseEntity<Void> sendNotificationByToken(@RequestBody FCMNotificationRequest requestDto) throws FirebaseMessagingException {
        fcmNotificationService.sendNotificationByToken(requestDto);
        return ResponseEntity.noContent().build();
    }
}