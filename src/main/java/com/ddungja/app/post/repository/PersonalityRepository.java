package com.ddungja.app.post.repository;

import com.ddungja.app.post.domain.personality.Personality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalityRepository extends JpaRepository<Personality, Long> {
}
