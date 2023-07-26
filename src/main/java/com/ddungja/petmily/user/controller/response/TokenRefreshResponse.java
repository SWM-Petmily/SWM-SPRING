package com.ddungja.petmily.user.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenRefreshResponse {

    private final String accessToken;
    private final String refreshToken;

    @Builder
    private TokenRefreshResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static TokenRefreshResponse from(String accessToken, String refreshToken) {
        return TokenRefreshResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
