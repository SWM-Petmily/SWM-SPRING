package com.ddungja.petmily.user.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserCreateRequest {

    private final Long certificationId;
    private final String nickname;
    private final String phone;

    @Builder
    private UserCreateRequest(Long certificationId, String nickname, String phone) {
        this.certificationId = certificationId;
        this.nickname = nickname;
        this.phone = phone;
    }
}
