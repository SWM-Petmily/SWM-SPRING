package com.ddungja.app.users.profileimage.infrastructure;


import com.ddungja.app.users.profileimage.domain.ProfileImage;
import com.ddungja.app.users.profileimage.service.port.ProfileImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProfileImageRepositoryImpl implements ProfileImageRepository {

    private final ProfileImageJpaRepository profileImageJpaRepository;

    @Override
    public Optional<ProfileImage> findById(Long profileImageId) {
        return profileImageJpaRepository.findById(profileImageId).map(ProfileImageEntity::toDomain);
    }

}
