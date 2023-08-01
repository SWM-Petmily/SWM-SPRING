package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.user.domain.certification.CertificationAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CertificationAttemptRepository extends JpaRepository<CertificationAttempt, Long> {

    Optional<CertificationAttempt> findByUserIdAndLastAttemptDate(Long id, LocalDate now);
}
