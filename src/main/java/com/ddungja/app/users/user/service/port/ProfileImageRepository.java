package com.ddungja.app.users.user.service.port;

import com.ddungja.app.users.user.domain.ProfileImage;

import java.util.Optional;

public interface ProfileImageRepository {
    Optional<ProfileImage> findById(Long profileImageId);
}
