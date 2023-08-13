package com.ddungja.petmily.user.service.port;

import com.ddungja.petmily.user.domain.certification.CertificationAttempt;

import java.time.LocalDate;
import java.util.Optional;

public interface CertificationAttemptRepository {
    CertificationAttempt save(CertificationAttempt certificationAttempt);

    Optional<CertificationAttempt> findByUserIdAndLastAttemptDate(Long userId, LocalDate attemptDate);
}
