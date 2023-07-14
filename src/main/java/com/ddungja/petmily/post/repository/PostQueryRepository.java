package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

public interface PostQueryRepository {

    @EntityGraph(attributePaths = {"like", "subCategory"})
    Page<Post> getMypost(Long userId, PostStatusType postStatusType, Pageable pageable);
}