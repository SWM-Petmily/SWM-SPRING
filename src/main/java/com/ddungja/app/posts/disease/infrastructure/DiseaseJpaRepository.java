package com.ddungja.app.posts.disease.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseJpaRepository extends JpaRepository<DiseaseEntity, Long> {
}
