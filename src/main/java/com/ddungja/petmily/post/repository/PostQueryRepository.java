package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.request.PostFilterRequest;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostQueryRepository {
    Page<Post> getMyPost(Long userId, PostStatusType postStatusType, Pageable pageable);

    Page<Post> getMainPosts(PostFilterRequest postStatusType, Pageable pageable);
    Page<Post> getMainPosts(Long userId,PostFilterRequest postStatusType, Pageable pageable);

}
