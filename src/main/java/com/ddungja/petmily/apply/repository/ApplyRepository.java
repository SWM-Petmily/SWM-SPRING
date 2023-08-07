package com.ddungja.petmily.apply.repository;

import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.domain.ApprovalType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long>, ApplyQueryRepository {

    @EntityGraph(attributePaths = {"applyExperiences", "user"})
    Optional<Apply> findDetailById(Long applyId);

    Optional<Apply> findByIdAndSellerId(Long applyId, Long sellerId);

    @EntityGraph(attributePaths = {"user"})
    Slice<Apply> findBySellerIdAndPostId(Long sellerId, Long postId, Pageable pageable);

    Optional<Apply> findByUserIdAndPostId(Long userId, Long postId);


    Optional<Apply> findByIdAndUserId(Long applyId, Long userId);

    int countByUserId(Long userId);

    List<Apply> findByPostIdAndApproval(Long postId, ApprovalType approvalType);
}
