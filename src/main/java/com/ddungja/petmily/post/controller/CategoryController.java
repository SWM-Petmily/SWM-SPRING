package com.ddungja.petmily.post.controller;

import com.ddungja.petmily.post.controller.response.MainCategoryResponse;
import com.ddungja.petmily.post.controller.response.SubCategoryResponse;
import com.ddungja.petmily.post.domain.SubCategory;
import com.ddungja.petmily.post.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "메인 카테고리 불러오기")
    @ApiResponse(responseCode = "200", description = "메인 카테고리 불러오기 성공", content = @Content(schema = @Schema(implementation = MainCategoryResponse.class)))
    @GetMapping
    public ResponseEntity<List<MainCategoryResponse>> getCategory(){
        log.info("메인 카테고리 불러오기");
        return ResponseEntity.ok(categoryService.getMainCategory().stream().map(MainCategoryResponse::from).toList());
    }

    @Operation(summary = "서브 카테고리 불러오기")
    @ApiResponse(responseCode = "200", description = "서브카테고리 불러오기 성공", content = @Content(schema = @Schema(implementation = SubCategoryResponse.class)))
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<SubCategoryResponse>> getSubCategory(@PathVariable Long categoryId){
        log.info("서브 카테고리 불러오기 categoryId = {}", categoryId);
        List<SubCategory> subCategories = categoryService.getSubCategory(categoryId);
        return ResponseEntity.ok(subCategories.stream().map(SubCategoryResponse::from).toList());
    }
}
