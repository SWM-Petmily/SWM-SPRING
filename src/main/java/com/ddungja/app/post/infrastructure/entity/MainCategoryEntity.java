package com.ddungja.app.post.infrastructure.entity;


import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.post.domain.MainCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "main_categories")
public class MainCategoryEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_category_id")
    private Long id;

    private String name;


    @Builder
    private MainCategoryEntity(Long id, String name, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static MainCategoryEntity from(MainCategory mainCategory) {
        return MainCategoryEntity.builder()
                .id(mainCategory.getId())
                .name(mainCategory.getName())
                .createDate(mainCategory.getCreateDate())
                .updateDate(mainCategory.getUpdateDate())
                .build();
    }

    public MainCategory toDomain() {
        return MainCategory.builder()
                .id(id)
                .name(name)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }

}
