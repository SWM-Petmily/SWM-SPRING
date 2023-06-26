package com.ddungja.app.post.infrastructure.adapter;

import com.ddungja.app.post.infrastructure.jpa.PersonalityJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonalityRepositoryImpl {
    private final PersonalityJpaRepository personalityJpaRepository;
}
