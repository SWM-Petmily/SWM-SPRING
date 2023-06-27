package com.ddungja.app.users.user.infrastructure.jpa;

import com.ddungja.app.users.user.domain.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceJpaRepository extends JpaRepository<Experience, Long> {
}
