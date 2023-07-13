package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.SubCategory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    List<SubCategoryNameAndId> findByMainCategoryId(Long mainCategoryId);

    Optional<SubCategory> findById(Long id);

    Optional<SubCategory> findByName(String name);

    interface SubCategoryNameAndId {
        String getName();
        Long getId();
    }
}
