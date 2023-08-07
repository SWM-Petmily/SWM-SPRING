package com.ddungja.petmily.apply.service;


import com.ddungja.petmily.apply.controller.response.ApplyUpdateRequest;
import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.domain.ApprovalType;
import com.ddungja.petmily.apply.domain.request.ApplyCreateRequest;
import com.ddungja.petmily.apply.domain.request.ApproveRequest;
import com.ddungja.petmily.apply.repository.ApplyRepository;
import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.common.domain.exception.ExceptionCode;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.repository.PostRepository;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public Slice<Apply> supportMyPost(Long userId, Long postId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        return applyRepository.findBySellerIdAndPostId(user.getId(), post.getId(), pageable);
    }

    public Page<Apply> getAppliedList(Long userId, ApprovalType approval, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return applyRepository.getApplyList(user.getId(), approval, pageable);
    }
    public int getApplyCount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return applyRepository.countByUserId(user.getId());
    }

    public Apply getDetailInfo(Long applyId) {
        return applyRepository.findDetailById(applyId).orElseThrow(() -> new CustomException(APPLY_NOT_FOUND));
    }

    @Transactional
    public Apply approve(Long sellerId, Long applyId, ApproveRequest approveRequest) {
        Apply apply = applyRepository.findByIdAndSellerId(applyId, sellerId).orElseThrow(() -> new CustomException(APPLY_NOT_FOUND));
        apply.approve(approveRequest.getApproval());
        return apply;
    }

    @Transactional
    public Apply apply(Long userId, Long postId, ApplyCreateRequest applyCreateRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        if (applyRepository.findByUserIdAndPostId(userId, postId).isPresent()) {
            throw new CustomException(APPLY_ALREADY_EXISTS);
        }
        return applyRepository.save(Apply.from(applyCreateRequest, user, post));
    }

    @Transactional
    public Apply cancel(Long userId, Long postId) {
        Apply apply = applyRepository.findByUserIdAndPostId(userId, postId).orElseThrow(() -> new CustomException(APPLY_NOT_FOUND));
        apply.cancel();
        return apply;
    }

    @Transactional
    public Apply modify(Long userId, Long applyId, ApplyUpdateRequest applyUpdateRequest){
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Apply apply = applyRepository.findByIdAndUserId(applyId, user.getId()).orElseThrow(() -> new CustomException(APPLY_NOT_FOUND));
        if (apply.getApproval() != ApprovalType.WAITING) {
            throw new CustomException(ExceptionCode.APPLY_CANT_MODIFY);
        }
        apply.modify(applyUpdateRequest);
        return apply;
    }

    public Boolean isApply(Long userId, Long postId) {
        return applyRepository.findByUserIdAndPostId(userId, postId).isPresent();
    }
}
