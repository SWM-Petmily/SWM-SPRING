package com.ddungja.petmily.apply.controller;

import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.domain.ApprovalType;
import com.ddungja.petmily.post.domain.post.GenderType;
import com.ddungja.petmily.post.domain.post.PostStatusType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApplyPostListResponse {
    private final Long applyId;
    private final Long postId;
    private final String region;
    //    private final String mainImageUrl;
    private final String name;
    private final GenderType gender;
    private final String birth;
    private final PostStatusType status;
    private final int likes;
    private final LocalDateTime createDate;
    private final ApprovalType approval;

    @Builder
    public ApplyPostListResponse(Long applyId, Long postId, String region, String name, GenderType gender, String birth, PostStatusType status, int likes, LocalDateTime createDate, ApprovalType approval) {
        this.applyId = applyId;
        this.postId = postId;
        this.region = region;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.status = status;
        this.likes = likes;
        this.createDate = createDate;
        this.approval = approval;
    }

    public static ApplyPostListResponse from(Apply apply) {
        return ApplyPostListResponse.builder()
                .applyId(apply.getId())
                .postId(apply.getPost().getId())
                .region(apply.getPost().getRegion())
                .name(apply.getPost().getName())
                .gender(apply.getPost().getGender())
                .status(apply.getPost().getStatus())
                .likes(0)
                .createDate(apply.getPost().getCreateDate())
                .approval(apply.getApproval())
                .birth(apply.getPost().getBirth())
                .build();
    }
}
