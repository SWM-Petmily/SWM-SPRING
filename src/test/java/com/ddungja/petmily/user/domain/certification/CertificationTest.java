package com.ddungja.petmily.user.domain.certification;

import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.mock.TestExpireTimeHolder;
import com.ddungja.petmily.user.domain.request.CertificationVerifyRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CertificationTest {

    @DisplayName("CertificationVerifyRequest로 인증번호를 받아 certificationNumber가 같으면 인증한다.")
    @Test
    void verify() {
        CertificationVerifyRequest certificationVerifyRequest = CertificationVerifyRequest.builder()
                .certificationNumber("123456")
                .build();

        Certification certification = Certification.builder()
                .id(1L)
                .expiredAt(new TestExpireTimeHolder(3).millis())
                .certificationNumber("123456")
                .phoneNumber("010-1234-5678")
                .isCertification(false)
                .build();

        certification.verify(certificationVerifyRequest);

        assertThat(certification.isCertification()).isTrue();
    }

    @DisplayName("CertificationVerifyRequest로 인증번호를 받아 certificationNumber가 다르면 에러를 던진다.")
    @Test
    void verifyFail_CERTIFICATION_NUMBER_NOT_MATCH() {
        CertificationVerifyRequest certificationVerifyRequest = CertificationVerifyRequest.builder()
                .certificationNumber("333333")
                .build();

        Certification certification = Certification.builder()
                .id(1L)
                .expiredAt(new TestExpireTimeHolder(3).millis())
                .certificationNumber("123456")
                .phoneNumber("010-1234-5678")
                .isCertification(false)
                .build();
        assertThatThrownBy(() -> {
            certification.verify(certificationVerifyRequest);
        }).isInstanceOf(CustomException.class);
    }


    @DisplayName("이미 인증을 받은 전화번호면 에러를 던진다.")
    @Test
    void verifyFail_CERTIFICATION_PHONE_ALREADY_EXISTS() {
        CertificationVerifyRequest certificationVerifyRequest = CertificationVerifyRequest.builder()
                .certificationNumber("123456")
                .build();

        Certification certification = Certification.builder()
                .id(1L)
                .expiredAt(new TestExpireTimeHolder(3).millis())
                .certificationNumber("123456")
                .phoneNumber("010-1234-5678")
                .isCertification(true)
                .build();

        assertThatThrownBy(() -> {
            certification.verify(certificationVerifyRequest);
        }).isInstanceOf(CustomException.class);
    }


    @DisplayName("인증번호가 만료되면 에러를 던진다.")
    @Test
    void verifyFail_CERTIFICATION_NUMBER_EXPIRED() {
        CertificationVerifyRequest certificationVerifyRequest = CertificationVerifyRequest.builder()
                .certificationNumber("123456")
                .build();

        Certification certification = Certification.builder()
                .id(1L)
                .expiredAt(new TestExpireTimeHolder(-1).millis())
                .certificationNumber("123456")
                .phoneNumber("010-1234-5678")
                .isCertification(false)
                .build();

        assertThatThrownBy(() -> {
            certification.verify(certificationVerifyRequest);
        }).isInstanceOf(CustomException.class);
    }

}