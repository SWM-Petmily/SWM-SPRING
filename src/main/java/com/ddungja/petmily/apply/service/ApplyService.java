package com.ddungja.petmily.apply.service;


import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.repository.ApplyRepository;
import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.post.domain.post.Post;
import com.ddungja.petmily.post.repository.PostRepository;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.POST_NOT_FOUND;
import static com.ddungja.petmily.common.domain.exception.ExceptionCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public Page<Apply> getByPostId(Long id, Long postId, Pageable pageable) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        return applyRepository.findBySellerIdAndPostId(user.getId(),  post.getId(), pageable);
    }

    public Page<Apply> getByUserId(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return applyRepository.findByUserId(user.getId(), pageable);
    }
}
