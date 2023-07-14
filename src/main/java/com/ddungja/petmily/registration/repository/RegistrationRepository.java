package com.ddungja.petmily.registration.repository;

import com.ddungja.petmily.registration.domain.Registration;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Optional<Registration> findByDogRegNo(String dogRegNo);
}
