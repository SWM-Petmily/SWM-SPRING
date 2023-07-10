package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.image.Image;
import com.ddungja.petmily.post.domain.post.GenderType;
import com.ddungja.petmily.post.domain.post.Post;
import com.ddungja.petmily.post.domain.post.PostStatusType;
import com.ddungja.petmily.user.domain.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostGetResponse {
    private final Long id;
    private final String mainCategory;
    private final String subCategory;
    private final String region;
    private final String name;
    @Enumerated(EnumType.STRING)
    private final GenderType gender;
    private final String birth;

    @Enumerated(EnumType.STRING)
    private final String neutered;
    private final int money;
    private final String reason; // 분양 이유
    private final String advantage; // 장점, 자랑
    private final String disadvantage; // 단점, 주의할 점
    private final String averageCost;// 평균비용
    private final String adopter; // 분양자
    @Enumerated(EnumType.STRING)
    private final PostStatusType status; // 분양상태

    private final List<ImageResponse> images;

    private final boolean isMine;

    @Builder
    public PostGetResponse(Long id, String mainCategory, String subCategory, String region, String name, GenderType gender, String birth, String neutered, int money, String reason, String advantage, String disadvantage, String averageCost, String adopter, PostStatusType status, List<ImageResponse> images, boolean isMine) {
        this.id = id;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.region = region;
        this.name = name;
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
        this.images = images;
        this.isMine = isMine;
    }

    @Builder
    public static PostGetResponse from(User user, Post post, List<Image> images) {
        return PostGetResponse.builder()
                .id(post.getId())
                .mainCategory(post.getMainCategory().getName())
                .subCategory(post.getSubCategory().getName())
                .region(post.getRegion())
                .name(post.getName())
                .gender(post.getGender())
                .birth(post.getBirth())
                .neutered(post.getNeutered().toString())
                .money(post.getMoney())
                .reason(post.getReason())
                .advantage(post.getAdvantage())
                .disadvantage(post.getDisadvantage())
                .averageCost(post.getAverageCost())
                .adopter(post.getAdopter())
                .status(post.getStatus())
                .images(ImageResponse.from(images))
                .isMine(user==null?false:user.getId().equals(post.getUser().getId()))
                .build();
    }
}