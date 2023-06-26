package com.ddungja.app.post.domain;

import com.ddungja.app.post.infrastructure.entity.MainCategoryEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class SubCategory {
    private final Long id;
    private final String name;
    private final MainCategoryEntity mainCategory;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;


    @Builder
    private SubCategory(Long id, String name, MainCategoryEntity mainCategory, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.mainCategory = mainCategory;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
