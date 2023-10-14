package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.user.domain.profile.Profile;
import com.ddungja.petmily.user.service.port.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class ProfileRepositoryImpl implements ProfileRepository {

    private final ProfileJpaRepository profileJpaRepository;

    @Override
    public Optional<Profile> findByUserId(Long userId) {
        return profileJpaRepository.findByUserId(userId);
    }

    @Override
    public Profile save(Profile profile) {
        return profileJpaRepository.save(profile);
    }

    @Override
    public void deleteByUserId(Long userId) {
        profileJpaRepository.deleteByUserId(userId);
    }
}
