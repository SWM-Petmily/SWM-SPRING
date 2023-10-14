package com.ddungja.petmily.like.repository;

import com.ddungja.petmily.like.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeJpaRepository extends JpaRepository<Like, Long>, LikeQueryRepository {

    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);

    int countByPostId(Long postId);

    int countByUserId(Long userId);

    void deleteByUserId(Long userId);
}
