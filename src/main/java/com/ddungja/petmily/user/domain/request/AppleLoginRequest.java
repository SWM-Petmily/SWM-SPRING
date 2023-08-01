package com.ddungja.petmily.user.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AppleLoginRequest {
    private final String idToken;
    private final String accessToken;

    @Builder
    private AppleLoginRequest(String idToken, String accessToken) {
        this.idToken = idToken;
        this.accessToken = accessToken;
    }
}
