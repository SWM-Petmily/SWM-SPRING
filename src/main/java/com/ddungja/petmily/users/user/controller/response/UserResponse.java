package com.ddungja.petmily.users.user.controller.response;

import com.ddungja.petmily.users.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {

    private final String email;
    private final String nickname;
    private final String birth;
    private final String phone;
    private final String provider;
    private final boolean isProfile;


    @Builder
    public UserResponse(String email, String nickname, String birth, String phone, String provider, boolean isProfile) {
        this.email = email;
        this.nickname = nickname;
        this.birth = birth;
        this.phone = phone;
        this.provider = provider;
        this.isProfile = isProfile;
    }

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .birth(user.getBirth())
                .phone(user.getPhone())
                .provider(user.getProvider())
                .isProfile(user.isProfile())
                .build();
    }
}
