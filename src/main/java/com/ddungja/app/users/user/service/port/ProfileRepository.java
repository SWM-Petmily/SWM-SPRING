package com.ddungja.app.users.user.service.port;

import com.ddungja.app.users.user.domain.Profile;

import java.util.Optional;

public interface ProfileRepository {
    Profile save(Profile profile);

    Optional<Profile> findByUserId(Long userId);
}
