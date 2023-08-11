package com.ddungja.petmily.user.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KaKaoLoginRequest {
    private final String accessToken;
    private final String tokenType;

    @Builder
    public KaKaoLoginRequest(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }
}
