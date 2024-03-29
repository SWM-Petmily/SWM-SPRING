package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.type.GenderType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MainPostResponse {
    private final Long id;
    private final String subCategory;
    private final String name;
    private final String thumbnailImage;
    private final String region;
    private final GenderType genderType;
    private final int age;
    private final int money;
    private final Boolean isLike;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private final LocalDateTime createdDate;

    @Builder
    public MainPostResponse( Long id, String name, String thumbnailImage, String subCategory, String region, GenderType genderType, int age, int money, Boolean isLike, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.thumbnailImage = thumbnailImage;
        this.subCategory = subCategory;
        this.region = region;
        this.genderType = genderType;
        this.age = age;
        this.money = money;
        this.isLike = isLike;
        this.createdDate = createdDate;
    }


    public static MainPostResponse from(Post post) {
        return MainPostResponse.builder()
                .id(post.getId())
                .name(post.getName())
                .thumbnailImage(post.getThumbnailImage())
                .subCategory(post.getSubCategory().getName())
                .genderType(post.getGender())
                .region(post.getRegion())
                .age(post.getAge())
                .money(post.getMoney())
                .isLike(false)
                .createdDate(post.getCreateDate())
                .build();
    }

    public static MainPostResponse from(Long userId, Post post) {
        return MainPostResponse.builder()
                .id(post.getId())
                .name(post.getName())
                .thumbnailImage(post.getThumbnailImage())
                .subCategory(post.getSubCategory().getName())
                .genderType(post.getGender())
                .region(post.getRegion())
                .age(post.getAge())
                .money(post.getMoney())
                .isLike(post.getLike().stream().anyMatch(like -> like.getUser().getId().equals(userId)))
                .createdDate(post.getCreateDate())
                .build();
    }
}
