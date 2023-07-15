package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCertificationResponse {

    private final Boolean isCertification;
    @Builder
    private UserCertificationResponse(Boolean isCertification) {
        this.isCertification = isCertification;
    }

    public static UserCertificationResponse from(User user) {
        return UserCertificationResponse.builder()
                .isCertification(user.isCertification())
                .build();
    }
}
