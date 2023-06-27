package com.ddungja.app.users.user.infrastructure.adapter;

import com.ddungja.app.users.user.domain.Experience;
import com.ddungja.app.users.user.infrastructure.jpa.ExperienceJpaRepository;
import com.ddungja.app.users.user.service.port.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExperienceRepositoryImpl implements ExperienceRepository {

    private final ExperienceJpaRepository experienceJpaRepository;

    @Override
    public List<Experience> saveAll(List<Experience> experiences) {
        return experienceJpaRepository.saveAll(experiences);
    }
}
