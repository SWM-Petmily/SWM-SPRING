package com.ddungja.app.post.service;

import com.ddungja.app.common.domain.exception.CustomException;
import com.ddungja.app.post.domain.MainCategory;
import com.ddungja.app.post.domain.SubCategory;
import com.ddungja.app.post.repository.MainCategoryRepository;
import com.ddungja.app.post.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ddungja.app.common.domain.exception.ExceptionCode.SUB_CATEGORY_NOT_FOUND;
import static com.ddungja.app.common.domain.exception.ExceptionCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Transactional
    public List<MainCategory> getMainCategory(){
        return mainCategoryRepository.findAll();
    }
    @Transactional
    public List<SubCategory.SubcategoryResponse> getSubCategory(Long categoryId){
        return subCategoryRepository.findIdAndNameByMainCategory_Id(categoryId);
    }
}
