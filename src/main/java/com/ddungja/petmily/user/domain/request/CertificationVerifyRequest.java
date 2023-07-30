package com.ddungja.petmily.user.domain.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class CertificationVerifyRequest {

    private String certificationNumber;

    @Builder
    private CertificationVerifyRequest(String certificationNumber) {
        this.certificationNumber = certificationNumber;
    }
}
