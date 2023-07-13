package com.ddungja.petmily.like.controller.response;

import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.post.domain.post.*;
import com.ddungja.petmily.post.domain.type.GenderType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class LikePostResponse {
    private final Long id;
    private final String name;
    private final String thumbnailImage;
    private final String subCategory;
    private final String region;
    @Enumerated(EnumType.STRING)
    private final GenderType gender;
    private final String birth;

    private final int like;

    private final boolean isMine;

    private final String createdDate;

    @Builder
    public LikePostResponse(Long id, String name, String image, String subCategory, String region, GenderType gender, String birth, int like, boolean isMine, String createdDate) {
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
    }


    public static List<LikePostResponse> from(List<Like> likes) {
        List<LikePostResponse> likePostResponses = new ArrayList<>();
        for(Like like : likes){
            Post post = like.getPost();
            LikePostResponse likePostResponse = LikePostResponse.builder()
                    .id(post.getId())
                    .name(post.getName())
                    .image(post.getThumbnailImage())
                    .subCategory(post.getSubCategory().getName())
                    .region(post.getRegion())
                    .birth(post.getBirth())
                    .gender(post.getGender())
                    .like(post.getLike().size())
                    .isMine(post.getUser().getId().equals(like.getUser().getId()))
                    .createdDate(post.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                    .build();
            likePostResponses.add(likePostResponse);
        }
        return likePostResponses;
    }
}
