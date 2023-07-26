package com.ddungja.petmily.user.domain.request;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CertificationPhoneVerifyRequest {

    private final Long certificationId;
    private final String phoneNumber;
    private final String certificationNumber;

    @Builder
    private CertificationPhoneVerifyRequest(Long certificationId, String phoneNumber, String certificationNumber) {
        this.certificationId = certificationId;
        this.phoneNumber = phoneNumber;
        this.certificationNumber = certificationNumber;
    }
}
