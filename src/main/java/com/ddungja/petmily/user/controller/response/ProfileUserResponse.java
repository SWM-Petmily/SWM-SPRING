package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.user.ProviderType;
import com.ddungja.petmily.user.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProfileUserResponse {
    private final String email;
    private final String nickname;
    private final String phone;
    private final ProviderType provider;
    private final Boolean isProfile;
    @Builder
    public ProfileUserResponse(String email, String nickname ,String phone, ProviderType provider, Boolean isProfile) {
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.provider = provider;
        this.isProfile = isProfile;
    }
    public static ProfileUserResponse from(User user) {
        return ProfileUserResponse.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .provider(user.getProvider())
                .isProfile(user.isProfile())
                .build();
    }
}
