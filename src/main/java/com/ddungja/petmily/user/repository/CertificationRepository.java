package com.ddungja.petmily.user.repository;


import com.ddungja.petmily.user.domain.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    Optional<Certification> findByUserIdAndPhoneNumber(Long userId, String phone);

    Optional<Certification> findByPhoneNumber(String phoneNumber);

}
