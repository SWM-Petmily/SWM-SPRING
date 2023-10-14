package com.ddungja.petmily.apply.repository;

import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.domain.ApprovalType;
import com.ddungja.petmily.apply.service.port.ApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ApplyRepositoryImpl implements ApplyRepository {

    private final ApplyJpaRepository applyJpaRepository;

    @Override
    public Page<Apply> findBySellerIdAndPostId(Long sellerId, Long postId, Pageable pageable) {
        return applyJpaRepository.findBySellerIdAndPostId(sellerId, postId, pageable);
    }

    @Override
    public Page<Apply> getApplyList(Long userId, ApprovalType approval, Pageable pageable) {
        return applyJpaRepository.getApplyList(userId, approval, pageable);
    }

    @Override
    public int countByUserId(Long userId) {
        return applyJpaRepository.countByUserId(userId);
    }

    @Override
    public Optional<Apply> findDetailById(Long applyId) {
        return applyJpaRepository.findDetailById(applyId);
    }

    @Override
    public Optional<Apply> findByIdAndSellerId(Long applyId, Long sellerId) {
        return applyJpaRepository.findByIdAndSellerId(applyId, sellerId);
    }

    @Override
    public Optional<Apply> findByUserIdAndPostId(Long userId, Long postId) {
        return applyJpaRepository.findByUserIdAndPostId(userId, postId);
    }

    @Override
    public Optional<Apply> findByIdAndUserId(Long applyId, Long userId) {
        return applyJpaRepository.findByIdAndUserId(applyId, userId);
    }

    @Override
    public Apply save(Apply apply) {
        return applyJpaRepository.save(apply);
    }

    @Override
    public void deleteByUserId(Long userId) {
        applyJpaRepository.deleteByUserId(userId);
    }
}
