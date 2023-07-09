package com.ddungja.petmily.post.domain.post;


import com.ddungja.petmily.common.domain.BaseTimeEntity;
import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.post.domain.MainCategory;
import com.ddungja.petmily.post.domain.SubCategory;
import com.ddungja.petmily.post.domain.image.Image;
import com.ddungja.petmily.users.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "posts")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "main_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MainCategory mainCategory;

    @JoinColumn(name = "sub_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SubCategory subCategory;

    private String thumbnailImage;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> like;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Image> images;

    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private String birth;
    private String name;
    private String region;

    @Enumerated(EnumType.STRING)
    private NeuteredType neutered;
    private int money;
    private String breeding;
    private String environment;
    private String reason;
    private String personality ;

    @Enumerated(EnumType.STRING)
    private VaccinatedType vaccination;

    @Enumerated(EnumType.STRING)
    private PostStatusType registration;
    private int views;
    private int reports;
    private int completion; // 0 : 미완료 1 : 완료

    @Enumerated(EnumType.STRING)
    private PostStatusType status;


    @Builder
    private Post(Long id, User user, MainCategory mainCategory, SubCategory subCategory, GenderType gender, String birth, String name, String region, NeuteredType neutered, int money, String breeding, String environment, String reason, String personality, VaccinatedType vaccination, PostStatusType registration, int views, int likes, int reports, int completion, int applys, PostStatusType status, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.user = user;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.gender = gender;
        this.birth = birth;
        this.name = name;
        this.region = region;
        this.neutered = neutered;
        this.money = money;
        this.breeding = breeding;
        this.environment = environment;
        this.reason = reason;
        this.personality = personality;
        this.vaccination = vaccination;
        this.registration = registration;
        this.views = views;
        this.reports = reports;
        this.completion = completion;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

}
