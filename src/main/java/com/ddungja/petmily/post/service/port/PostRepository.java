package com.ddungja.petmily.post.service.port;

import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.request.PostFilterRequest;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostRepository {
    Post save(Post post);

    Optional<Post> findPostById(Long postId);

    Page<Post> getMyPost(Long userId, PostStatusType postStatusType, Pageable pageable);

    Optional<Post> findById(Long postId);

    Page<Post> getMainPosts(Long userId, PostFilterRequest postFilterRequest, Pageable pageable);

    Page<Post> getMainPosts(PostFilterRequest postFilterRequest, Pageable pageable);
}
