package com.ddungja.petmily.user.controller.response;

import com.ddungja.petmily.user.domain.Certification;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CertificationPhoneNumberResponse {
    private final Long certificationId;

    @Builder
    private CertificationPhoneNumberResponse(Long certificationId) {
        this.certificationId = certificationId;
    }

    public static CertificationPhoneNumberResponse from(Certification certification) {
        return CertificationPhoneNumberResponse.builder()
                .certificationId(certification.getId())
                .build();
    }
}
