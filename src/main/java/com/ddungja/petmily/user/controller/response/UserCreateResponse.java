package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateResponse {
    private final Long userId;

    @Builder
    private UserCreateResponse(Long userId) {
        this.userId = userId;
    }

    public static UserCreateResponse from(User user) {
        return UserCreateResponse.builder()
                .userId(user.getId())
                .build();
    }
}
