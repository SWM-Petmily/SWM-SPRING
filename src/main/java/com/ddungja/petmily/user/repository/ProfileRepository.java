package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.user.domain.profile.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @EntityGraph(attributePaths = {"user", "experiences", "profileImage"})
    Optional<Profile> findByUserId(Long userId);
}
