package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLoginResponse {
    private final Long userId;
    private final String accessToken;
    private final String refreshToken;
    private final Boolean isCertification;
    @Builder
    public UserLoginResponse(Long userId, String accessToken, String refreshToken, Boolean isCertification) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.isCertification = isCertification;
    }

    public static UserLoginResponse from(User user, String accessToken, String refreshToken) {
        return UserLoginResponse.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .isCertification(user.isCertification())
                .build();
    }
}
