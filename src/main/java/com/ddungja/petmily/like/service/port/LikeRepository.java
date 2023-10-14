package com.ddungja.petmily.like.service.port;

import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LikeRepository {
    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);

    Like save(Like like);

    Page<Like> findByUserIdAndPostStatus(Long userId, PostStatusType postStatusType, Pageable pageable);

    void delete(Like like);

    int countByPostId(Long postId);

    int countByUserId(Long userId);

    void deleteByUserId(Long userId);
}
