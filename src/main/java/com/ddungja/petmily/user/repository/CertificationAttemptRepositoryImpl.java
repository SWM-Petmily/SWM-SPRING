package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.user.domain.certification.CertificationAttempt;
import com.ddungja.petmily.user.service.port.CertificationAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CertificationAttemptRepositoryImpl implements CertificationAttemptRepository {
    private final CertificationAttemptJpaRepository certificationAttemptJpaRepository;


    @Override
    public CertificationAttempt save(CertificationAttempt certificationAttempt) {
        return certificationAttemptJpaRepository.save(certificationAttempt);
    }

    @Override
    public Optional<CertificationAttempt> findByUserIdAndLastAttemptDate(Long userId, LocalDate attemptDate) {
        return certificationAttemptJpaRepository.findByUserIdAndLastAttemptDate(userId, attemptDate);
    }
}
