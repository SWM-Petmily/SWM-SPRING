package com.ddungja.app.post.infrastructure.jpa;

import com.ddungja.app.post.infrastructure.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageJpaRepository extends JpaRepository<ImageEntity, Long> {
}
