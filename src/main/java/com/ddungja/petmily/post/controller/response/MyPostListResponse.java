package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.type.GenderType;
import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

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
    private final PostStatusType status;

    @Builder
    private MyPostListResponse(Long id, String name, String thumbnailImage, String subCategory, String region, GenderType gender, String birth, int like, String createdDate, PostStatusType status) {
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
    }

    public static MyPostListResponse from(Post post) {
        int[] date = Arrays.stream(post.getBirth().split("-")).mapToInt(Integer::parseInt).toArray();
        LocalDate start = LocalDate.of(date[0], date[1], 1);
        LocalDate end = LocalDate.now();
        return MyPostListResponse.builder()
                .id(post.getId())
                .name(post.getName())
                .thumbnailImage(post.getThumbnailImage())
                .subCategory(post.getSubCategory().getName())
                .gender(post.getGender())
                .status(post.getStatus())
                .region(post.getRegion())
                .birth(Long.toString(ChronoUnit.MONTHS.between(start, end)))
                .like(post.getLike().size())
                .createdDate(post.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .build();

    }
}
