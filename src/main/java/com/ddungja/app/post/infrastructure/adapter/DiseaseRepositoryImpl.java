package com.ddungja.app.post.infrastructure.adapter;

import com.ddungja.app.post.infrastructure.jpa.DiseaseJpaRepository;
import com.ddungja.app.post.service.port.DiseaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DiseaseRepositoryImpl implements DiseaseRepository {
    private final DiseaseJpaRepository diseaseJpaRepository;
}
