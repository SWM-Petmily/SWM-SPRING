package com.ddungja.app.post.infrastructure.jpa;

import com.ddungja.app.post.infrastructure.entity.MainCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainCategoryJpaRepository extends JpaRepository<MainCategoryEntity, Long> {
}
