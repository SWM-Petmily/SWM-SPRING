package com.ddungja.petmily.user.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserUpdateRequest {
    private final String nickname;
    private final Long profileImageId;
    @Builder
    private UserUpdateRequest(String nickname, Long profileImageId) {
        this.nickname = nickname;
        this.profileImageId = profileImageId;
    }
}

