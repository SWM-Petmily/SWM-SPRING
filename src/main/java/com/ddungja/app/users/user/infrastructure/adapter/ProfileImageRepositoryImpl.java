package com.ddungja.app.users.user.infrastructure.adapter;


import com.ddungja.app.users.user.domain.ProfileImage;
import com.ddungja.app.users.user.infrastructure.entity.ProfileImageEntity;
import com.ddungja.app.users.user.infrastructure.jpa.ProfileImageJpaRepository;
import com.ddungja.app.users.user.service.port.ProfileImageRepository;
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
