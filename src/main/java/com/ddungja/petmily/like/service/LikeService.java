package com.ddungja.petmily.like.service;

import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.like.repository.LikeRepository;
import com.ddungja.petmily.post.domain.post.Post;
import com.ddungja.petmily.post.repository.PostRepository;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.LIKE_IS_EXISTS;
import static com.ddungja.petmily.common.domain.exception.ExceptionCode.LIKE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Transactional
    public Like like(User user, Post post){
        if(likeRepository.findByPostIdAndUserId(post.getId(), user.getId()).isPresent()){
            throw new CustomException(LIKE_IS_EXISTS);
        }
        Like like = Like.from(user, post);
        likeRepository.save(like);
        return like;
    }

    @Transactional
    public void unlike(Like like){
        likeRepository.delete(like);
    }

    @Transactional
    public Like getLike (Long postId, Long userId){
        return likeRepository.findByPostIdAndUserId(postId, userId).orElseThrow(()-> new CustomException(LIKE_NOT_FOUND));
    }

    @Transactional
    public void isLike(Long postId, Long userId){

    }

    @Transactional
    public void getLikeCount(Long postId){

    }

    @Transactional
    public List<Like> getLikeList(Long userId){
        return likeRepository.findAllByUserId(userId);
    }





}
