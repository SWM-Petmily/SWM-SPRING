package com.ddungja.petmily.apply.service.port;

import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.domain.ApprovalType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ApplyRepository {
    Page<Apply> findBySellerIdAndPostId(Long sellerId, Long postId, Pageable pageable);

    Page<Apply> getApplyList(Long userId, ApprovalType approval, Pageable pageable);

    int countByUserId(Long userId);

    Optional<Apply> findDetailById(Long applyId);

    Optional<Apply> findByIdAndSellerId(Long applyId, Long sellerId);

    Optional<Apply> findByUserIdAndPostId(Long userId, Long postId);

    Optional<Apply> findByIdAndUserId(Long applyId, Long userId);

    Apply save(Apply apply);

     void deleteByUserId(Long userId);
}
