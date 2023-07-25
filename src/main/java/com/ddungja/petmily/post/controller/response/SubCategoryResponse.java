package com.ddungja.petmily.post.controller.response;


import com.ddungja.petmily.post.domain.SubCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SubCategoryResponse {
    private final Long subCategoryId;
    private final String name;

    @Builder
    private SubCategoryResponse(Long subCategoryId, String name) {
        this.subCategoryId = subCategoryId;
        this.name = name;
    }

    public static SubCategoryResponse from(SubCategory subCategory) {
        return SubCategoryResponse.builder()
                .subCategoryId(subCategory.getId())
                .name(subCategory.getName())
                .build();
    }
}




