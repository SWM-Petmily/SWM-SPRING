package com.ddungja.petmily.like.repository;

import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LikeQueryRepository {
    Page<Like> findByUserIdAndPostStatus(Long userId, PostStatusType postStatusType, Pageable pageable);
}
