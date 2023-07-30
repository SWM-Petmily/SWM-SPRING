package com.ddungja.petmily.user.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UserCreateRequest {
    private  String nickname;

    @Builder
    private UserCreateRequest(String nickname) {
        this.nickname = nickname;
    }
}
