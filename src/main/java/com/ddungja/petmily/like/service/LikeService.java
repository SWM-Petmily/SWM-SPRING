package com.ddungja.petmily.like.service;

import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.like.service.port.LikeRepository;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.ddungja.petmily.post.service.port.PostRepository;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.service.port.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ddungja.petmily.common.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Builder
public class LikeService {

    private final LikeRepository likeRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Transactional
    public Like like(Long userId, Long postId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        if (likeRepository.findByPostIdAndUserId(post.getId(), user.getId()).isPresent()) {
            throw new CustomException(LIKE_IS_EXISTS);
        }
        return likeRepository.save(Like.from(user, post));
    }

    public Page<Like> getLikeList(Long userId, PostStatusType postStatusType, Pageable pageable) {
        userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return likeRepository.findByUserIdAndPostStatus(userId, postStatusType, pageable);
    }

    @Transactional
    public void unlike(Long userId, Long postId) {
        Like like = likeRepository.findByPostIdAndUserId(postId, userId).orElseThrow(() -> new CustomException(LIKE_NOT_FOUND));
        likeRepository.delete(like);
    }

    public Boolean isLike(Long userId, Long postId) {
        return likeRepository.findByPostIdAndUserId(postId, userId).isPresent();
    }

    public int getLikeCountByPostId(Long postId) {
        return likeRepository.countByPostId(postId);
    }

    public int getLikeCountByUser(User user) {
        return likeRepository.countByUserId(user.getId());
    }

    @Transactional
    public void deleteByUserId(Long userId) {
        likeRepository.deleteByUserId(userId);
    }
}
