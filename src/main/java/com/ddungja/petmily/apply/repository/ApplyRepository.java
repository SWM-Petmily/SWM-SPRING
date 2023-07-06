package com.ddungja.petmily.apply.repository;

import com.ddungja.petmily.apply.domain.Apply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

    @EntityGraph(attributePaths = {"applyExperiences"})
    Page<Apply> findBySellerIdAndPostId(Long userId, Long postId, Pageable pageable);

    @EntityGraph(attributePaths = {"applyExperiences"})
    Page<Apply> findByUserId(Long userId, Pageable pageable);

}
