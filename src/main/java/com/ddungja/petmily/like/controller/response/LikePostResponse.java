package com.ddungja.petmily.like.controller.response;

import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.type.GenderType;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LikePostResponse {
    private final Long id;
    private final String name;
    private final String thumbnailImage;
    private final String subCategory;
    private final String region;
    private final GenderType gender;
    private final String birth;
    private final int like;
    private final Boolean isMine;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private final LocalDateTime createdDate;
    private final PostStatusType postStatus;

    @Builder
    public LikePostResponse(Long id, String name, String image, String subCategory, String region, GenderType gender, String birth, int like, Boolean isMine, LocalDateTime createdDate, PostStatusType postStatus) {
        this.id = id;
        this.name = name;
        this.thumbnailImage = image;
        this.subCategory = subCategory;
        this.region = region;
        this.gender = gender;
        this.birth = birth;
        this.like = like;
        this.isMine = isMine;
        this.createdDate = createdDate;
        this.postStatus = postStatus;
    }

    public static LikePostResponse from(Like like) {
        Post post = like.getPost();
        return LikePostResponse.builder()
                .id(post.getId())
                .name(post.getName())
                .image(post.getThumbnailImage())
                .subCategory(post.getSubCategory().getName())
                .region(post.getRegion())
                .birth(post.getBirth())
                .gender(post.getGender())
                .like(post.getLike().size())
                .isMine(post.getUser().getId().equals(like.getUser().getId()))
                .createdDate(post.getCreateDate())
                .postStatus(post.getStatus())
                .build();
    }
}
