package com.ddungja.app.post.infrastructure.entity;

import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.post.domain.SubCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "sub_categories")
public class SubCategoryEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_category_id")
    private Long id;

    private String name;

    @JoinColumn(name = "main_category_id")
    @ManyToOne
    private MainCategoryEntity mainCategory;

    @Builder
    private SubCategoryEntity(Long id, String name, MainCategoryEntity mainCategory, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.mainCategory = mainCategory;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }


    public static SubCategoryEntity from(SubCategory subCategory) {
        return SubCategoryEntity.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .mainCategory(subCategory.getMainCategory())
                .createDate(subCategory.getCreateDate())
                .updateDate(subCategory.getUpdateDate())
                .build();
    }

    public SubCategory toDomain() {
        return SubCategory.builder()
                .id(id)
                .name(name)
                .mainCategory(mainCategory)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }
}

