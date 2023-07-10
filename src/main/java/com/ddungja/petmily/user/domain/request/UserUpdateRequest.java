package com.ddungja.petmily.user.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {
    private String nickname;

    @Builder
    private UserUpdateRequest(String nickname) {
        this.nickname = nickname;
    }
}

