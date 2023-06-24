package com.ddungja.app.posts.personality.infrastructure;

import com.ddungja.app.posts.personality.domain.Personality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalityJpaRepository extends JpaRepository<PersonalityEntity, Long> {
}
