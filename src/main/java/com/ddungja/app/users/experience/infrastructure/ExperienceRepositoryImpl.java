package com.ddungja.app.users.experience.infrastructure;

import com.ddungja.app.users.experience.domain.Experience;
import com.ddungja.app.users.experience.service.port.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExperienceRepositoryImpl implements ExperienceRepository {

    private final ExperienceJpaRepository experienceJpaRepository;

    @Override
    public List<Experience> saveAll(List<Experience> experiences) {
        List<ExperienceEntity> list = experiences.stream().map(ExperienceEntity::from).toList();
        return experienceJpaRepository.saveAll(list).stream().map(ExperienceEntity::toDomain).toList();
    }
}
