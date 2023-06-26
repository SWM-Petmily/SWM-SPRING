package com.ddungja.app.users.user.infrastructure.jpa;

import com.ddungja.app.users.user.infrastructure.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileJpaRepository extends JpaRepository<ProfileEntity, Long> {
}
