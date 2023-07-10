package com.ddungja.petmily.user.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KaKaoLoginRequest {

    private final String tokenType;
    private final String accessToken;

    @Builder
    private KaKaoLoginRequest(String tokenType, String accessToken) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
    }
}
