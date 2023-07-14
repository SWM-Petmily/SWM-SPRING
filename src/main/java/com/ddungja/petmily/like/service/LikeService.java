package com.ddungja.petmily.like.service;

import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.like.repository.LikeRepository;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.ddungja.petmily.post.repository.PostRepository;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Transactional
    public Like like(Long userId, Long postId){
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        if(likeRepository.findByPostIdAndUserId(postId, userId).isPresent()){
            throw new CustomException(LIKE_IS_EXISTS);
        }
        Like like = Like.from(user, post);
        likeRepository.save(like);
        return like;
    }

    public Page<Like> getLikeList(Long userId, PostStatusType postStatusType, Pageable pageable) {
        userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return likeRepository.adfs(userId, postStatusType, pageable);
    }

    @Transactional
    public void unlike(Long userId, Long postId) {
        Like like = likeRepository.findByPostIdAndUserId(postId, userId).orElseThrow(() -> new CustomException(LIKE_NOT_FOUND));
        likeRepository.delete(like);
    }
}
