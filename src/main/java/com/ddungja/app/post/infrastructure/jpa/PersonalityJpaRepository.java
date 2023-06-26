package com.ddungja.app.post.infrastructure.jpa;

import com.ddungja.app.post.infrastructure.entity.PersonalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalityJpaRepository extends JpaRepository<PersonalityEntity, Long> {
}
