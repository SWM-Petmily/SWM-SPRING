package com.ddungja.app.post.repository;

import com.ddungja.app.post.domain.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
}
