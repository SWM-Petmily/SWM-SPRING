package com.ddungja.petmily.user.repository;


import com.ddungja.petmily.user.domain.Certification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    @EntityGraph(attributePaths = {"user"})
    Optional<Certification> findFirstByUserIdOrderByIdDesc(Long userId);
}
