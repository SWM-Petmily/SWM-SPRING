package com.ddungja.petmily.user.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CertificationPhoneNumberRequest {
    private final String phoneNumber;

    @Builder
    private CertificationPhoneNumberRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
