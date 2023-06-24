package com.ddungja.app.profile.infrastructure;

import com.ddungja.app.profile.service.port.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class ProfileRepositoryImpl implements ProfileRepository {

    private final ProfileJpaRepository profileJpaRepository;
}
