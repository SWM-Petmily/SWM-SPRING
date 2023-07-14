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
@Transactional(readOnly = true)
public class CategoryService {

    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;


    public List<MainCategory> getMainCategory(){
        return mainCategoryRepository.findAll();
    }
    public List<SubCategory> getSubCategory(Long categoryId){
        return subCategoryRepository.findByMainCategoryId(categoryId);
    }
}
