package com.ddungja.petmily.user.domain;


import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.user.domain.request.CertificationPhoneVerifyRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.*;
import static com.ddungja.petmily.common.domain.exception.ExceptionCode.CERTIFICATION_NUMBER_EXPIRED;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "certifications")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certification_id")
    private Long id;
    private String phoneNumber;
    private LocalDateTime expiredAt;
    private String certificationNumber;
    private boolean isCertification;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    private Certification(Long id, String phone, LocalDateTime expiredAt, String certificationNumber, boolean isCertification, User user) {
        this.id = id;
        this.phoneNumber = phone;
        this.expiredAt = expiredAt;
        this.certificationNumber = certificationNumber;
        this.isCertification = isCertification;
        this.user = user;
    }

    public void certification() {
        this.isCertification = true;
    }

    public void validation(CertificationPhoneVerifyRequest certificationPhoneVerifyRequest, Long userId) {
        if (!user.getId().equals(userId)) {
            throw new CustomException(CERTIFICATION_NOT_FOUND);
        }
        if (!phoneNumber.equals(certificationPhoneVerifyRequest.getPhoneNumber())) {
            throw new CustomException(CERTIFICATION_NOT_FOUND);
        }
        if (!certificationNumber.equals(certificationPhoneVerifyRequest.getCertificationNumber())) {
            throw new CustomException(CERTIFICATION_NUMBER_NOT_MATCH);
        }
        if (isCertification) {
            throw new CustomException(CERTIFICATION_PHONE_ALREADY_EXISTS);
        }
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new CustomException(CERTIFICATION_NUMBER_EXPIRED);
        }
    }
}
