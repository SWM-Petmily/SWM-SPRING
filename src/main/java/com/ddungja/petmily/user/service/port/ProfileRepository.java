package com.ddungja.petmily.user.service.port;

import com.ddungja.petmily.user.domain.profile.Profile;

import java.util.Optional;

public interface ProfileRepository {
    Optional<Profile> findByUserId(Long userId);

    Profile save(Profile profile);
}

