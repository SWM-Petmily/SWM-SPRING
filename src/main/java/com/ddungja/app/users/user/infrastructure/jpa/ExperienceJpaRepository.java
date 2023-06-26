package com.ddungja.app.users.user.infrastructure.jpa;

import com.ddungja.app.users.user.infrastructure.entity.ExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceJpaRepository extends JpaRepository<ExperienceEntity, Long> {
}
