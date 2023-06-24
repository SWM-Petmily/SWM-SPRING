package com.ddungja.app.posts.post.infrastructure;


import com.ddungja.app.posts.maincategory.infrastructure.MainCategoryEntity;
import com.ddungja.app.posts.subcategory.infrastructure.SubCategoryEntity;
import com.ddungja.app.users.user.infrastructure.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "posts")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;


    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @JoinColumn(name = "main_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MainCategoryEntity mainCategory;

    @JoinColumn(name = "sub_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SubCategoryEntity subCategory;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String birth;
    private String name;
    private String region;

    @Enumerated(EnumType.STRING)
    private Neutered neutered;
    private int money;
    private String breeding;
    private String environment;
    private String reason;
    private String vaccination;
    private int views;
    private int likes;
    private int reports;
    private int completion;
    private int applys;

    @Enumerated(EnumType.STRING)
    private Status status;
}
