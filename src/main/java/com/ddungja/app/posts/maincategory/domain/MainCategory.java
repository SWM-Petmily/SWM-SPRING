package com.ddungja.app.posts.maincategory.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MainCategory {
    private final Long id;
    private final String name;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    @Builder
    private MainCategory(Long id, String name, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
