package com.ddungja.app.post.infrastructure;

import com.ddungja.app.post.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
}
