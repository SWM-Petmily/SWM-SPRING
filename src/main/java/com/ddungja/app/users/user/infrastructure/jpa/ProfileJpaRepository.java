package com.ddungja.app.users.user.infrastructure.jpa;

import com.ddungja.app.users.user.domain.Profile;
import com.ddungja.app.users.user.infrastructure.entity.ProfileEntity;
import com.querydsl.core.group.GroupBy;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileJpaRepository extends JpaRepository<ProfileEntity, Long> {



    @EntityGraph(attributePaths = {"user", "experiences", "profileImage"})
    Optional<ProfileEntity> findByUserId(Long userId);
}
