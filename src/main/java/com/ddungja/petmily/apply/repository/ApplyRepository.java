package com.ddungja.petmily.apply.repository;

import com.ddungja.petmily.apply.domain.Apply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long>, ApplyQueryRepository {

    @Query("select a from Apply a left join fetch a.applyExperiences where a.id = :applyId")
    Optional<Apply> findByApplyId(Long applyId);

    Optional<Apply> findByIdAndSellerId(Long applyId, Long sellerId);

    @EntityGraph(attributePaths = {"user"})
    Page<Apply> findBySellerIdAndPostId(Long sellerId, Long postId, Pageable pageable);

    Optional<Apply> findByUserIdAndPostId(Long userId, Long postId);


}
