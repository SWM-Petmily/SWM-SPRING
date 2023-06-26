package com.ddungja.app.users.user.infrastructure.jpa;

import com.ddungja.app.users.user.infrastructure.entity.ProfileImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageJpaRepository extends JpaRepository<ProfileImageEntity, Long> {
}
