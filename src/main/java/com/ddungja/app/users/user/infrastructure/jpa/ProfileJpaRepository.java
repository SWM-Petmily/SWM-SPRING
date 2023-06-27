package com.ddungja.app.users.user.infrastructure.jpa;

import com.ddungja.app.users.user.domain.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileJpaRepository extends JpaRepository<Profile, Long> {



    @EntityGraph(attributePaths = {"user", "experiences", "profileImage"})
    Optional<Profile> findByUserId(Long userId);
}
