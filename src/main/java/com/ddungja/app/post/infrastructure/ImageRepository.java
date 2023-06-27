package com.ddungja.app.post.infrastructure;

import com.ddungja.app.post.domain.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
