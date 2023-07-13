package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.post.*;
import com.ddungja.petmily.post.domain.type.GenderType;
import com.ddungja.petmily.post.domain.type.NeuteredType;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;


@Getter
public class PostCreateResponse {

    private final Long id;
    private final Long mainCategory;
    private final Long subCategory;
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
    private PostStatusType status; // 분양상태

    @Builder
    public PostCreateResponse(Long id, Long mainCategory, Long subCategory, String region, GenderType gender, String birth, NeuteredType neutered, int money, String reason, String advantage, String disadvantage, String averageCost, String adopter, PostStatusType status) {
        this.id = id;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
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
    }


    public static PostCreateResponse from(Post post) {
        return PostCreateResponse.builder()
                .id(post.getId())
                .mainCategory(post.getMainCategory().getId())
                .subCategory(post.getSubCategory().getId())
                .region(post.getRegion())
                .gender(post.getGender())
                .birth(post.getBirth())
                .neutered(post.getNeutered())
                .money(post.getMoney())
                .reason(post.getReason())
                .advantage(post.getAdvantage())
                .disadvantage(post.getDisadvantage())
                .averageCost(post.getAverageCost())
                .adopter(post.getAdopter())
                .status(post.getStatus())
                .build();
    }

}
