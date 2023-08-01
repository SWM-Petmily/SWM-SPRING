package com.ddungja.petmily.apply.controller.response;

import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.domain.ApprovalType;
import com.ddungja.petmily.post.domain.type.GenderType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApplyPostListResponse {
    private final Long applyId;
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
    private final ApprovalType status;
    private final int age;

    @Builder
    private ApplyPostListResponse(Long applyId, Long postId, String name, String thumbnailImage, String subCategory, String region, GenderType gender, String birth, int like, LocalDateTime createdDate, ApprovalType approval, int age) {
        this.applyId = applyId;
        this.postId = postId;
        this.name = name;
        this.thumbnailImage = thumbnailImage;
        this.subCategory = subCategory;
        this.region = region;
        this.gender = gender;
        this.birth = birth;
        this.like = like;
        this.createdDate = createdDate;
        this.status = approval;
        this.age = age;
    }
    public static ApplyPostListResponse from(Apply apply) {
        return  ApplyPostListResponse.builder()
                .applyId(apply.getId())
                .postId(apply.getPost().getId())
                .name(apply.getPost().getName())
                .thumbnailImage(apply.getPost().getThumbnailImage())
                .subCategory(apply.getPost().getSubCategory().getName())
                .region(apply.getRegion())
                .gender(apply.getPost().getGender())
                .age(apply.getPost().getAge())
                .birth(apply.getPost().getBirth())
                .like(apply.getPost().getLike().size())
                .approval(apply.getApproval())
                .createdDate(apply.getPost().getCreateDate())
                .build();
    }
}
