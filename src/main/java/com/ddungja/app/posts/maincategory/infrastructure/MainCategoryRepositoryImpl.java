package com.ddungja.app.posts.maincategory.infrastructure;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MainCategoryRepositoryImpl {
    private final MainCategoryJpaRepository mainCategoryJpaRepository;
}
