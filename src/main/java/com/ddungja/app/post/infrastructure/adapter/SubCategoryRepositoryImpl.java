package com.ddungja.app.post.infrastructure.adapter;

import com.ddungja.app.post.infrastructure.jpa.SubCategoryJpaRepository;
import com.ddungja.app.post.service.port.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SubCategoryRepositoryImpl implements SubCategoryRepository {
    private final SubCategoryJpaRepository subCategoryJpaRepository;

}
