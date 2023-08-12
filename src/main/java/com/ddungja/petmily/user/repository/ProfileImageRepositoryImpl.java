package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.user.domain.profile.ProfileImage;
import com.ddungja.petmily.user.service.port.ProfileImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProfileImageRepositoryImpl implements ProfileImageRepository {
    private final ProfileImageJpaRepository profileImageJpaRepository;

    @Override
    public Optional<ProfileImage> findById(Long profileImageId) {
        return profileImageJpaRepository.findById(profileImageId);
    }

    @Override
    public ProfileImage save(ProfileImage profileImage) {
        return profileImageJpaRepository.save(profileImage);
    }
}
