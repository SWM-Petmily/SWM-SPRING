package com.ddungja.app.posts.subcategory.infrastructure;

import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.posts.maincategory.infrastructure.MainCategoryEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

}

