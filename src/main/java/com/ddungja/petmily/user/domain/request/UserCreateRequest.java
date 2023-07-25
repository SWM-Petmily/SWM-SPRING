package com.ddungja.petmily.user.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateRequest {

    private final String nickname;
    private final String phone;

    @Builder
    private UserCreateRequest(String nickname, String phone) {
        this.nickname = nickname;
        this.phone = phone;
    }
}
