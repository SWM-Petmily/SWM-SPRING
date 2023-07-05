package com.ddungja.app.post.domain;

import com.ddungja.app.common.domain.BaseTimeEntity;
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
public class SubCategory{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_category_id")
    private Long id;

    private String name;

    @JoinColumn(name = "main_category_id")
    @ManyToOne
    private MainCategory mainCategory;

    @Builder
    private SubCategory(Long id, String name, MainCategory mainCategory) {
        this.id = id;
        this.name = name;
        this.mainCategory = mainCategory;
    }

    public interface SubcategoryResponse {
        Long getId();
        String getName();
    }

}

