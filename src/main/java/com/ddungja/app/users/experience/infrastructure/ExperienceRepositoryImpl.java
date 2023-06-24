package com.ddungja.app.users.experience.infrastructure;

import com.ddungja.app.users.experience.service.port.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExperienceRepositoryImpl implements ExperienceRepository {

    private final ExperienceJpaRepository experienceJpaRepository;
}
