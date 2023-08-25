package com.ddungja.petmily.user.service;


import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.user.domain.certification.CertificationAttempt;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.service.port.CertificationAttemptRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.ddungja.petmily.common.exception.ExceptionCode.USER_ALREADY_CERTIFICATION;

@Service
@Transactional
@RequiredArgsConstructor
@Builder
public class CertificationAttemptService {

    private final CertificationAttemptRepository certificationAttemptRepository;

    public void attempt(User user) {
        if (user.isCertification()) {
            throw new CustomException(USER_ALREADY_CERTIFICATION);
        }
        CertificationAttempt certificationAttempt = certificationAttemptRepository.findByUserIdAndLastAttemptDate(user.getId(), LocalDate.now()).orElseGet(() -> CertificationAttempt.builder()
                .user(user)
                .lastAttemptDate(LocalDate.now())
                .attemptCount(0)
                .build());
        certificationAttempt.attempt();
        certificationAttemptRepository.save(certificationAttempt);
    }
}
