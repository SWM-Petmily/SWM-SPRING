package com.ddungja.petmily.like.repository;

import com.ddungja.petmily.like.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);

    List<Like> findAllByUserId(Long userId);
}
