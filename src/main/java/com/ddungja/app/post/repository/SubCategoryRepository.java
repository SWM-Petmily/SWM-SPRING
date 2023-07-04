package com.ddungja.app.post.repository;

import com.ddungja.app.post.domain.MainCategory;
import com.ddungja.app.post.domain.SubCategory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    List<SubCategory.SubcategoryResponse> findIdAndNameByMainCategory_Id(Long categoryId);

    Optional<SubCategory> findById(Long id);

}
