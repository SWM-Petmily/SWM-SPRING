package com.ddungja.app.users.user.infrastructure.jpa;

import com.ddungja.app.users.user.domain.Profile;

import java.util.Optional;

public interface ProfileQueryRepository {
    Optional<Profile> findByUserId(Long userId);
}
