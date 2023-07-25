package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLoginResponse {

    private final String accessToken;
    private final String refreshToken;
    private final Boolean isCertification;
    @Builder
    private UserLoginResponse(String accessToken, String refreshToken, Boolean isCertification) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.isCertification = isCertification;
    }

    public static UserLoginResponse from(User user, String accessToken, String refreshToken) {
        return UserLoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .isCertification(user.isCertification())
                .build();
    }
}
