package com.ddungja.petmily.user.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CertificationPhoneNumberRequest {
    private  String phoneNumber;

    @Builder
    public CertificationPhoneNumberRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
