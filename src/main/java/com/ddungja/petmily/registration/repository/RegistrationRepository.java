package com.ddungja.petmily.registration.repository;

import com.ddungja.petmily.registration.domain.Registration;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @EntityGraph(attributePaths = {"petSubCategory"})
    List<Registration> findByUserId(Long userId);

    Optional<Registration> findByRegistrationNumber(String dogRegNo);
    @EntityGraph(attributePaths = {"petSubCategory","petSubCategory.mainCategory"})
    Optional<Registration> findByIdAndUserId(Long id, Long userId);
}
