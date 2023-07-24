package com.ddungja.petmily.apply.repository;

import com.ddungja.petmily.apply.domain.Apply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long>, ApplyQueryRepository {

    @EntityGraph(attributePaths = {"applyExperiences", "user"})
    Optional<Apply> findDetailById(Long applyId);

    Optional<Apply> findByIdAndSellerId(Long applyId, Long sellerId);

    @EntityGraph(attributePaths = {"user"})
    Page<Apply> findBySellerIdAndPostId(Long sellerId, Long postId, Pageable pageable);

    Optional<Apply> findByUserIdAndPostId(Long userId, Long postId);


    Optional<Apply> findByIdAndUserId(Long applyId, Long userId);
}
