package com.ddungja.petmily.user.domain.apple;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class AppleLoginRequest {
    private String token;

    @Builder
    private AppleLoginRequest(String token) {
        this.token = token;
    }
}
