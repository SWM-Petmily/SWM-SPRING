package com.ddungja.app.users.user.infrastructure.jpa;

import com.ddungja.app.users.user.infrastructure.entity.ProfileEntity;

import java.util.Optional;

public interface ProfileQueryRepository {
    Optional<ProfileEntity> findByUserId(Long userId);
}
