package com.ddungja.app.posts.post.infrastructure;


import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.posts.maincategory.infrastructure.MainCategoryEntity;
import com.ddungja.app.posts.post.domain.Gender;
import com.ddungja.app.posts.post.domain.Neutered;
import com.ddungja.app.posts.post.domain.Post;
import com.ddungja.app.posts.post.domain.Status;
import com.ddungja.app.posts.subcategory.infrastructure.SubCategoryEntity;
import com.ddungja.app.users.user.infrastructure.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "posts")
public class PostEntity extends BaseTimeEntity {

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


    @Builder
    private PostEntity(Long id, UserEntity user, MainCategoryEntity mainCategory, SubCategoryEntity subCategory, Gender gender, String birth, String name, String region, Neutered neutered, int money, String breeding, String environment, String reason, String vaccination, int views, int likes, int reports, int completion, int applys, Status status, LocalDateTime createDate, LocalDateTime updateDate) {
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
        this.vaccination = vaccination;
        this.views = views;
        this.likes = likes;
        this.reports = reports;
        this.completion = completion;
        this.applys = applys;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static PostEntity from(Post post) {
        return PostEntity.builder()
                .id(post.getId())
                .user(post.getUser())
                .mainCategory(post.getMainCategory())
                .subCategory(post.getSubCategory())
                .gender(post.getGender())
                .birth(post.getBirth())
                .name(post.getName())
                .region(post.getRegion())
                .neutered(post.getNeutered())
                .money(post.getMoney())
                .breeding(post.getBreeding())
                .environment(post.getEnvironment())
                .reason(post.getReason())
                .vaccination(post.getVaccination())
                .views(post.getViews())
                .likes(post.getLikes())
                .reports(post.getReports())
                .completion(post.getCompletion())
                .applys(post.getApplys())
                .status(post.getStatus())
                .createDate(post.getCreateDate())
                .updateDate(post.getUpdateDate())
                .build();
    }

    public Post toDomain() {
        return Post.builder()
                .id(id)
                .user(user)
                .mainCategory(mainCategory)
                .subCategory(subCategory)
                .gender(gender)
                .birth(birth)
                .name(name)
                .region(region)
                .neutered(neutered)
                .money(money)
                .breeding(breeding)
                .environment(environment)
                .reason(reason)
                .vaccination(vaccination)
                .views(views)
                .likes(likes)
                .reports(reports)
                .completion(completion)
                .applys(applys)
                .status(status)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }

}
