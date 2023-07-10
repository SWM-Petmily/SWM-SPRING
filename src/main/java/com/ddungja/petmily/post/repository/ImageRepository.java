package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.image.Image;
import com.ddungja.petmily.post.domain.image.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByPostIdAndImageType(Long postId, ImageType type);
}
