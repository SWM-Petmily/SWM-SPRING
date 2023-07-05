package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
