package com.ddungja.app.users.profile.infrastructure;

import com.ddungja.app.users.profile.domain.Profile;
import com.ddungja.app.users.profile.service.port.ProfileRepository;
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
