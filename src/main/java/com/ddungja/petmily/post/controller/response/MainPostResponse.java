package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.type.GenderType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MainPostResponse {
    private final Long id;
    private final String subCategory;
    private final String name;
    private final String thumbnailImage;
    private final String region;
    private final GenderType genderType;
    private final String birth;
    private final Boolean isLike;
    private final String createdDate;

    @Builder
    public MainPostResponse(Long id, String name, String thumbnailImage, String subCategory, String region, GenderType genderType, String birth, Boolean isLike, String createdDate) {
        this.id = id;
        this.name = name;
        this.thumbnailImage = thumbnailImage;
        this.subCategory = subCategory;
        this.region = region;
        this.genderType = genderType;
        this.birth = birth;
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
                .birth(post.getBirth())
                .isLike(false)
                .createdDate(post.getCreateDate().toString())
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
                .birth(post.getBirth())
                .isLike(post.getLike().stream().anyMatch(like -> like.getUser().getId().equals(userId)))
                .createdDate(post.getCreateDate().toString())
                .build();
    }
}