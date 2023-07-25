package com.ddungja.petmily.user.controller.response;


import lombok.Builder;
import lombok.Getter;

@Getter
public class CertificationResponse {
    private final String certificationNumber;

    @Builder
    private CertificationResponse(String certificationNumber) {
        this.certificationNumber = certificationNumber;
    }

    public static CertificationResponse from(String certificationNumber) {
        return CertificationResponse.builder()
                .certificationNumber(certificationNumber)
                .build();
    }
}
