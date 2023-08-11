package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSignUpResponse {

    private final Long userId;
    @Builder
    public UserSignUpResponse(Long userId) {
        this.userId = userId;
    }

    public static UserSignUpResponse from(User user) {
        return UserSignUpResponse.builder()
                .userId(user.getId())
                .build();
    }
}
