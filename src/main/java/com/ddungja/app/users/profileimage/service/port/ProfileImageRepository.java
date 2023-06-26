package com.ddungja.app.users.profileimage.service.port;

import com.ddungja.app.users.profileimage.domain.ProfileImage;

import java.util.Optional;

public interface ProfileImageRepository {
    Optional<ProfileImage> findById(Long profileImageId);
}
