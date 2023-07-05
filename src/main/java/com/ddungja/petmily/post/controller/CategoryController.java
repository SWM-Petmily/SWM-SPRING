package com.ddungja.petmily.post.controller;

import com.ddungja.petmily.post.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<?> getCategory(){
        log.info("메인 카테고리 불러오기");
        return ResponseEntity.ok(categoryService.getMainCategory());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getSubCategory(@PathVariable Long categoryId){
        log.info("서브 카테고리 불러오기 categoryId = {}", categoryId);
        return ResponseEntity.ok(categoryService.getSubCategory(categoryId));
    }
}
