package com.ddungja.app.users.user.infrastructure.adapter;

import com.ddungja.app.users.user.domain.Profile;
import com.ddungja.app.users.user.infrastructure.entity.ProfileEntity;
import com.ddungja.app.users.user.infrastructure.jpa.ProfileJpaRepository;
import com.ddungja.app.users.user.service.port.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class ProfileRepositoryImpl implements ProfileRepository {

    private final ProfileJpaRepository profileJpaRepository;

    @Override
    public Profile save(Profile profile) {
        return profileJpaRepository.save(ProfileEntity.from(profile)).toDomain();
    }
}
