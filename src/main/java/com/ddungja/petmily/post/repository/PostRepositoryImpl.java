package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.request.PostFilterRequest;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.ddungja.petmily.post.service.port.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final PostJpaRepository postJpaRepository;
    @Override
    public Post save(Post post) {
        return postJpaRepository.save(post);
    }

    @Override
    public Optional<Post> findPostById(Long postId) {
        return postJpaRepository.findPostById(postId);
    }

    @Override
    public Page<Post> getMyPost(Long userId, PostStatusType postStatusType, Pageable pageable) {
        return postJpaRepository.getMyPost(userId, postStatusType, pageable);
    }

    @Override
    public Optional<Post> findById(Long postId) {
        return postJpaRepository.findById(postId);
    }

    @Override
    public Page<Post> getMainPosts(Long userId, PostFilterRequest postFilterRequest, Pageable pageable) {
        return postJpaRepository.getMainPosts(userId, postFilterRequest, pageable);
    }

    @Override
    public Page<Post> getMainPosts(PostFilterRequest postFilterRequest, Pageable pageable) {
        return postJpaRepository.getMainPosts(postFilterRequest, pageable);
    }
}
