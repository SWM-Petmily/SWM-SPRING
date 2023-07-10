package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLoginResponse {
    private final Boolean isCertification;

    @Builder
    private UserLoginResponse(Boolean isCertification) {
        this.isCertification = isCertification;
    }

    public static UserLoginResponse from(User user) {
        return UserLoginResponse.builder()
                .isCertification(user.isCertification())
                .build();
    }
}
