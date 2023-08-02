package com.ddungja.petmily.like.repository;

import com.ddungja.petmily.like.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long>, LikeQueryRepository {

    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);

    int countByPostId(Long postId);

//    @EntityGraph(attributePaths = {"post", "post.like" ,"post.subCategory"})
//    Page<Like> findByUserIdAnd(Long userId, PostStatusType postStatusType, Pageable pageable);
}
