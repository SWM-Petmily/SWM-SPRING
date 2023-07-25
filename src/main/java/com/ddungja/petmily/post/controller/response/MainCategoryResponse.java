package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.MainCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MainCategoryResponse {
    private final Long mainCategoryId;
    private final String name;

    @Builder
    private MainCategoryResponse(Long mainCategoryId, String name) {
        this.mainCategoryId = mainCategoryId;
        this.name = name;
    }

    public static MainCategoryResponse from(MainCategory mainCategory) {
        return MainCategoryResponse.builder()
                .mainCategoryId(mainCategory.getId())
                .name(mainCategory.getName())
                .build();
    }
}

