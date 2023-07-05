package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.personality.Personality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalityRepository extends JpaRepository<Personality, Long> {
}
