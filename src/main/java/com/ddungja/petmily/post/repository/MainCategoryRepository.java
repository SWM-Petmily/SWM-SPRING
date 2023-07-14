package com.ddungja.petmily.post.repository;

import com.ddungja.petmily.post.domain.MainCategory;
import com.ddungja.petmily.user.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MainCategoryRepository extends JpaRepository<MainCategory, Long> {
    List<MainCategory> findAll();

    Optional<MainCategory> findById(Long id);

    MainCategory findByName(String name);
}
