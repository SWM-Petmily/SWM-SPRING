package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
}
