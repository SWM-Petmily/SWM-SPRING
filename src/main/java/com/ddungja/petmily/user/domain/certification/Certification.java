package com.ddungja.petmily.user.domain.certification;


import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.domain.request.CertificationVerifyRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.ddungja.petmily.common.exception.ExceptionCode.*;

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
    private Certification(Long id, String phoneNumber, LocalDateTime expiredAt, String certificationNumber, boolean isCertification, User user) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.expiredAt = expiredAt;
        this.certificationNumber = certificationNumber;
        this.isCertification = isCertification;
        this.user = user;
    }

    public void certificate() {
        this.isCertification = true;
    }

    public void verify(CertificationVerifyRequest certificationPhoneVerifyRequest) {
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

    public void signUpVerify() {
        if (!isCertification) {
            throw new CustomException(CERTIFICATION_NOT_COMPLETE);
        }
        if (user.isCertification()) {
            throw new CustomException(USER_ALREADY_CERTIFICATION);
        }
    }
}
