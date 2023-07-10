package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.post.GenderType;
import com.ddungja.petmily.post.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

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
    private final String createdDate;

    @Builder
    public MyPostListResponse(Long id, String name, String thumbnailImage, String subCategory, String region, GenderType gender, String birth, int like, String createdDate) {
        this.postId = id;
        this.name = name;
        this.thumbnailImage = thumbnailImage;
        this.subCategory = subCategory;
        this.region = region;
        this.gender = gender;
        this.birth = birth;
        this.like = like;
        this.createdDate = createdDate;
    }

    public static MyPostListResponse from(Post post) {
        return MyPostListResponse.builder()
                .id(post.getId())
                .name(post.getName())
                .thumbnailImage(post.getThumbnailImage())
                .subCategory(post.getSubCategory().getName())
                .region(post.getRegion())
                .birth(post.getBirth())
                .like(post.getLike().size())
                .createdDate(post.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .build();

    }
}
