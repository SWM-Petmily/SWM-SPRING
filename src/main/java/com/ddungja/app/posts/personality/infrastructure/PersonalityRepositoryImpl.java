package com.ddungja.app.posts.personality.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonalityRepositoryImpl {
    private final PersonalityJpaRepository personalityJpaRepository;
}
