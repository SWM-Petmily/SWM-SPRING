package com.ddungja.petmily.like.repository;

import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.like.service.port.LikeRepository;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    private final LikeJpaRepository likeJpaRepository;
    @Override
    public Optional<Like> findByPostIdAndUserId(Long postId, Long userId) {
        return likeJpaRepository.findByPostIdAndUserId(postId, userId);
    }

    @Override
    public Like save(Like like) {
        return likeJpaRepository.save(like);
    }

    @Override
    public Page<Like> findByUserIdAndPostStatus(Long userId, PostStatusType postStatusType, Pageable pageable) {
        return likeJpaRepository.findByUserIdAndPostStatus(userId, postStatusType, pageable);
    }

    @Override
    public void delete(Like like) {
        likeJpaRepository.delete(like);
    }

    @Override
    public int countByPostId(Long postId) {
        return likeJpaRepository.countByPostId(postId);
    }

    @Override
    public int countByUserId(Long userId) {
        return likeJpaRepository.countByUserId(userId);
    }

    @Override
    public void deleteByUserId(Long userId) {
        likeJpaRepository.deleteByUserId(userId);
    }
}
