package com.ddungja.petmily.post.domain;


import com.ddungja.petmily.common.domain.BaseTimeEntity;
import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.post.domain.request.PostCreateRequest;
import com.ddungja.petmily.post.domain.type.GenderType;
import com.ddungja.petmily.post.domain.type.NeuteredType;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.ddungja.petmily.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> like;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private String birth;
    private String name;
    private String region;

    @Enumerated(EnumType.STRING)
    private NeuteredType neutered;
    private int money;
    private String reason; // 분양 이유
    private String advantage; // 장점, 자랑
    private String disadvantage; // 단점, 주의할 점
    private String averageCost;// 평균비용
    private String adopter; // 분양자

    @Enumerated(EnumType.STRING)
    private PostStatusType status; // 분양상태

    private int views;
    private int reports;

    @Builder
    private Post(Long id, User user, MainCategory mainCategory, SubCategory subCategory, String thumbnailImage, List<Like> like, List<Image> images, GenderType gender, String birth, String name, String region, NeuteredType neutered, int money, String reason, String advantage, String disadvantage, String averageCost, String adopter, PostStatusType status, int views, int reports) {
        this.id = id;
        this.user = user;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.thumbnailImage = thumbnailImage;
        this.like = like;
        this.images = images;
        this.gender = gender;
        this.birth = birth;
        this.name = name;
        this.region = region;
        this.neutered = neutered;
        this.money = money;
        this.reason = reason;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.averageCost = averageCost;
        this.adopter = adopter;
        this.status = status;
        this.views = views;
        this.reports = reports;
    }

    public static Post from(PostCreateRequest postCreateRequest, User user, MainCategory mainCategory, SubCategory subCategory) {
        return Post.builder()
                .user(user)
                .mainCategory(mainCategory)
                .subCategory(subCategory)
                .name(postCreateRequest.getName())
                .gender(postCreateRequest.getGender())
                .birth(postCreateRequest.getBirth())
                .region(postCreateRequest.getRegion())
                .money(postCreateRequest.getMoney())
                .neutered(postCreateRequest.getNeutered())
                .reason(postCreateRequest.getReason())
                .advantage(postCreateRequest.getAdvantage())
                .disadvantage(postCreateRequest.getDisadvantage())
                .averageCost(postCreateRequest.getAverageCost())
                .adopter(postCreateRequest.getAdopter())
                .status(postCreateRequest.getStatus())
                .views(0)
                .reports(0)
                .build();
    }

    public void createThumbnailImage(PostCreateRequest postCreateRequest) {
        this.thumbnailImage = postCreateRequest.getPostImages().get(0).getUrl();
    }
}
