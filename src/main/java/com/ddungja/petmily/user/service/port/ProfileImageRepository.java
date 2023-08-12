package com.ddungja.petmily.user.service.port;

import com.ddungja.petmily.user.domain.profile.ProfileImage;

import java.util.Optional;

public interface ProfileImageRepository {
    Optional<ProfileImage> findById(Long profileImageId);

    ProfileImage save(ProfileImage profileImage);
}
