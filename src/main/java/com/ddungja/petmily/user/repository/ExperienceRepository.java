package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.user.domain.profile.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
