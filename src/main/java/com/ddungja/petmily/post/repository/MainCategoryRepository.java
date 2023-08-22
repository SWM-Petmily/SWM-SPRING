package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MainCategoryRepository extends JpaRepository<MainCategory, Long> {
    List<MainCategory> findAll();

    Optional<MainCategory> findById(Long id);

    Optional<MainCategory> findByName(String name);
}
