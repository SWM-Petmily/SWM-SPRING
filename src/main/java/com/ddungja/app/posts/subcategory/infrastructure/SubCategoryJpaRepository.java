package com.ddungja.app.posts.subcategory.infrastructure;

import com.ddungja.app.posts.subcategory.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryJpaRepository extends JpaRepository<SubCategory, Long> {
}
