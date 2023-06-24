package com.ddungja.app.posts.subcategory.infrastructure;

import com.ddungja.app.posts.subcategory.service.port.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SubCategoryRepositoryImpl implements SubCategoryRepository {
    private final SubCategoryJpaRepository subCategoryJpaRepository;

}
