package com.ddungja.petmily.user.service;


import com.ddungja.petmily.user.domain.certification.CertificationAttempt;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.service.port.CertificationAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class CertificationAttemptService {

    private final CertificationAttemptRepository certificationAttemptRepository;

    public void attempt(User user) {
        CertificationAttempt certificationAttempt = certificationAttemptRepository.findByUserIdAndLastAttemptDate(user.getId(), LocalDate.now()).orElseGet(() -> CertificationAttempt
                .builder()
                .lastAttemptDate(LocalDate.now())
                .user(user)
                .attemptCount(0)
                .build());
        certificationAttempt.attempt();
        certificationAttemptRepository.save(certificationAttempt);
    }
}
