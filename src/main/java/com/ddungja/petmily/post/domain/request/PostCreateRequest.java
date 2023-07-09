package com.ddungja.petmily.post.domain.request;

import com.ddungja.petmily.post.domain.MainCategory;
import com.ddungja.petmily.post.domain.SubCategory;
import com.ddungja.petmily.post.domain.post.*;
import com.ddungja.petmily.users.user.domain.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostCreateRequest {
    private final Long mainCategory;
    private final Long subCategory;
    private final String name;

    private final String region;
    @Enumerated(EnumType.STRING)
    private final GenderType gender;
    private final String birth;
    @Enumerated(EnumType.STRING)
    private final NeuteredType neutered;
    private final int money;
    private final String reason; // 분양 이유
    private final String advantage; // 장점, 자랑
    private final String disadvantage; // 단점, 주의할 점
    private final String averageCost;// 평균비용
    private final String adopter; // 분양자

    @Enumerated(EnumType.STRING)
    private final PostStatusType status;
    private final List<ImageCreateRequest> postImages;
    private final List<ImageCreateRequest> vaccinationImages;
    private final List<ImageCreateRequest> medicalCheckImages;
    private final List<DiseaseRequest> diseases;

    @Builder
    public PostCreateRequest(Long mainCategory, Long subCategory, String name, String region, GenderType gender, String birth, NeuteredType neutered, int money, String reason, String advantage, String disadvantage, String averageCost, String adopter, PostStatusType status, List<ImageCreateRequest> postImages, List<ImageCreateRequest> vaccinationImages, List<ImageCreateRequest> medicalCheckImages, List<DiseaseRequest> diseases) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.name = name;
        this.region = region;
        this.gender = gender;
        this.birth = birth;
        this.neutered = neutered;
        this.money = money;
        this.reason = reason;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.averageCost = averageCost;
        this.adopter = adopter;
        this.status = status;
        this.postImages = postImages;
        this.vaccinationImages = vaccinationImages;
        this.medicalCheckImages = medicalCheckImages;
        this.diseases = diseases;
    }

    public Post toEntity(User user, MainCategory mainCategory, SubCategory subCategory) {
        return Post.builder()
                .user(user)
                .mainCategory(mainCategory)
                .subCategory(subCategory)
                .name(name)
                .gender(gender)
                .birth(birth)
                .region(region)
                .neutered(neutered)
                .money(money)
                .reason(reason)
                .advantage(advantage)
                .disadvantage(disadvantage)
                .averageCost(averageCost)
                .adopter(adopter)
                .views(0)
                .reports(0)
                .status(status)
                .build();
    }

}

