package com.ddungja.app.users.user.infrastructure.adapter;

import com.ddungja.app.users.user.domain.Profile;
import com.ddungja.app.users.user.infrastructure.jpa.ProfileJpaRepository;
import com.ddungja.app.users.user.service.port.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class ProfileRepositoryImpl implements ProfileRepository {

    private final ProfileJpaRepository profileJpaRepository;

    @Override
    public Profile save(Profile profile) {
        return profileJpaRepository.save(profile);
    }

    @Override
    public Optional<Profile> findByUserId(Long userId) {
        return profileJpaRepository.findByUserId(userId);
    }
}
