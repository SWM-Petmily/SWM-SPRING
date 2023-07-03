package com.ddungja.app.post.repository;

import com.ddungja.app.post.domain.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainCategoryRepository extends JpaRepository<MainCategory, Long> {
    List<MainCategory> findAll();

}
