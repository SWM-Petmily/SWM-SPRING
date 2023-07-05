package com.ddungja.petmily.post.service;

import com.ddungja.petmily.post.domain.MainCategory;
import com.ddungja.petmily.post.domain.SubCategory;
import com.ddungja.petmily.post.repository.MainCategoryRepository;
import com.ddungja.petmily.post.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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