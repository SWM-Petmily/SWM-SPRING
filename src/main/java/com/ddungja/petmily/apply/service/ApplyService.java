package com.ddungja.petmily.apply.service;


import com.ddungja.petmily.apply.domain.request.ApplyCreateRequest;
import com.ddungja.petmily.apply.domain.request.ApproveRequest;
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
import org.springframework.transaction.annotation.Transactional;

import static com.ddungja.petmily.apply.domain.ApprovalType.WAITING;
import static com.ddungja.petmily.common.domain.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public Page<Apply> getByPostId(Long userId, Long postId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        return applyRepository.findBySellerIdAndPostIdAndApproval(user.getId(), post.getId(), pageable, WAITING);
    }

    public Page<Apply> getByUserId(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return applyRepository.findByUserId(user.getId(), pageable);
    }


    public Apply findById(Long applyId) {
        return applyRepository.findByApplyId(applyId).orElseThrow(() -> new CustomException(APPLY_NOT_FOUND));
    }

    public Apply approve(Long sellerId, Long applyId, ApproveRequest approveRequest) {
        Apply apply = applyRepository.findByIdAndSellerId(applyId, sellerId).orElseThrow(() -> new CustomException(APPLY_NOT_FOUND));
        apply.approve(approveRequest.getApproval());
        return apply;
    }

    public Apply apply(Long userId, Long postId, ApplyCreateRequest applyCreateRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        applyRepository.findByUserIdAndPostId(userId, postId).orElseThrow(() -> new CustomException(APPLY_ALREADY_EXISTS));
        return Apply.from(applyCreateRequest, user, post);
    }

    public Apply cancel(Long userId, Long postId) {
        Apply apply = applyRepository.findByUserIdAndPostId(userId, postId).orElseThrow(() -> new CustomException(APPLY_NOT_FOUND));
        apply.cancel();
        return apply;
    }
}
