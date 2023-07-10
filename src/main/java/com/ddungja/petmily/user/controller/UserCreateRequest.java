package com.ddungja.petmily.user.controller;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateRequest {

    private final String nickname;
    private final String phone;
    private final String birth;

    @Builder
    private UserCreateRequest(String nickname, String phone, String birth) {
        this.nickname = nickname;
        this.phone = phone;
        this.birth = birth;
    }
}
