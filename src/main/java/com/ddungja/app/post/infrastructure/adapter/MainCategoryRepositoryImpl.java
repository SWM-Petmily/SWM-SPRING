package com.ddungja.app.post.infrastructure.adapter;


import com.ddungja.app.post.infrastructure.jpa.MainCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MainCategoryRepositoryImpl {
    private final MainCategoryJpaRepository mainCategoryJpaRepository;
}
