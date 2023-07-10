package com.ddungja.petmily.apply.repository;

import com.ddungja.petmily.apply.domain.ApplyExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyExperienceRepository extends JpaRepository<ApplyExperience, Long> {
    List<ApplyExperience> findByApplyId(Long applyId);
}
