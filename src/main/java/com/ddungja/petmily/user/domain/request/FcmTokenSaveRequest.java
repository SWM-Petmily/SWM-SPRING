package com.ddungja.petmily.user.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class FcmTokenSaveRequest {
    private String fcmToken;

    @Builder
    public FcmTokenSaveRequest(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
