package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.type.GenderType;
import com.ddungja.petmily.post.domain.type.NeuteredType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
public class PostCreateResponse {

    private final Long postId;
    private final Long mainCategory;
    private final Long subCategory;
    private final String region;
    private final GenderType gender;
    private final String birth;
    private final NeuteredType neutered;
    private final int money;
    private final String reason; // 분양 이유
    private final String advantage; // 장점, 자랑
    private final String disadvantage; // 단점, 주의할 점
    private final String averageCost;// 평균비용
    private final String adopter; // 분양자
    private final List<PostCreateImageResponse> images;

    @Builder
    private PostCreateResponse(Long id, Long mainCategory, Long subCategory, String region, GenderType gender, String birth, NeuteredType neutered, int money, String reason, String advantage, String disadvantage, String averageCost, String adopter,  List<PostCreateImageResponse> images) {
        this.postId = id;
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
        this.images = images;
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
                .images(post.getImages() != null? post.getImages().stream().map(PostCreateImageResponse::from).toList() : null)
                .build();
    }

}
