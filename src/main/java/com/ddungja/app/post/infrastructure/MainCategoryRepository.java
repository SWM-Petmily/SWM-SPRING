package com.ddungja.app.post.infrastructure;

import com.ddungja.app.post.domain.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainCategoryRepository extends JpaRepository<MainCategory, Long> {
}
