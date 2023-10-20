package com.ddungja.petmily.fcm.request;

import lombok.Builder;
import lombok.Getter;

@Getter

public class FCMNotificationRequest {
    private final Long targetUserId;
    private final String title;
    private final String body;

    @Builder
    public FCMNotificationRequest(Long targetUserId, String title, String body) {
        this.targetUserId = targetUserId;
        this.title = title;
        this.body = body;
    }
}