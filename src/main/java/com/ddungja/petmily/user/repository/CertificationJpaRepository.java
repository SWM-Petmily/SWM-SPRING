package com.ddungja.petmily.user.repository;


import com.ddungja.petmily.user.domain.certification.Certification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificationJpaRepository extends JpaRepository<Certification, Long> {
    @EntityGraph(attributePaths = {"user"})
    Optional<Certification> findFirstByUserIdOrderByIdDesc(Long userId);
}
