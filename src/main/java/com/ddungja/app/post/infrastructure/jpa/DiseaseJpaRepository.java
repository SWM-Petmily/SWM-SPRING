package com.ddungja.app.post.infrastructure.jpa;

import com.ddungja.app.post.infrastructure.entity.DiseaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseJpaRepository extends JpaRepository<DiseaseEntity, Long> {
}
