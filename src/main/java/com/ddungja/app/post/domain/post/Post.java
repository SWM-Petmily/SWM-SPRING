package com.ddungja.app.post.domain.post;


import com.ddungja.app.common.domain.BaseTimeEntity;
import com.ddungja.app.post.domain.MainCategory;
import com.ddungja.app.post.domain.SubCategory;
import com.ddungja.app.users.user.domain.User;
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
    private String personality ;

    @Enumerated(EnumType.STRING)
    private Type vaccination;

    @Enumerated(EnumType.STRING)
    private Type registration;



    private int views;
    private int reports;
    private int completion; // 0 : 미완료 1 : 완료

    @Enumerated(EnumType.STRING)
    private PostStatus status;


    @Builder
    private Post(Long id, User user, MainCategory mainCategory, SubCategory subCategory, Gender gender, String birth, String name, String region, Neutered neutered, int money, String breeding, String environment, String reason, String personality, Type vaccination, Type registration, int views, int likes, int reports, int completion, int applys, PostStatus status, LocalDateTime createDate, LocalDateTime updateDate) {
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

    public static Post from(com.ddungja.app.post.domain.post.Post post) {
        return Post.builder()
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
                .personality(post.getPersonality())
                .vaccination(post.getVaccination())
                .views(post.getViews())
                .reports(post.getReports())
                .completion(post.getCompletion())
                .status(post.getStatus())
                .createDate(post.getCreateDate())
                .updateDate(post.getUpdateDate())
                .build();
    }

    public com.ddungja.app.post.domain.post.Post toDomain() {
        return builder()
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
                .reports(reports)
                .completion(completion)
                .status(status)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }

}
