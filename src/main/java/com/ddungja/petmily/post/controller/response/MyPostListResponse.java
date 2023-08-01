package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.type.GenderType;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyPostListResponse {
    private final Long postId;
    private final String name;
    private final String thumbnailImage;
    private final String subCategory;
    private final String region;
    private final GenderType gender;
    private final String birth;
    private final int like;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private final LocalDateTime createdDate;
    private final PostStatusType status;
    private final int age;

    @Builder
    private MyPostListResponse(Long id, String name, String thumbnailImage, String subCategory, String region, GenderType gender, String birth, int like, LocalDateTime createdDate, PostStatusType status, int age) {
        this.postId = id;
        this.name = name;
        this.thumbnailImage = thumbnailImage;
        this.subCategory = subCategory;
        this.region = region;
        this.gender = gender;
        this.birth = birth;
        this.like = like;
        this.createdDate = createdDate;
        this.status = status;
        this.age = age;
    }

    public static MyPostListResponse from(Post post) {
        return MyPostListResponse.builder()
                .id(post.getId())
                .name(post.getName())
                .thumbnailImage(post.getThumbnailImage())
                .subCategory(post.getSubCategory().getName())
                .gender(post.getGender())
                .status(post.getStatus())
                .region(post.getRegion())
                .age(post.getAge())
                .birth(post.getBirth())
                .like(post.getLike().size())
                .createdDate(post.getCreateDate())
                .build();

    }
}
