package com.ddungja.app.posts.disease.infrastructure;

import com.ddungja.app.posts.disease.service.port.DiseaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DiseaseRepositoryImpl implements DiseaseRepository {
    private final DiseaseJpaRepository diseaseJpaRepository;
}
