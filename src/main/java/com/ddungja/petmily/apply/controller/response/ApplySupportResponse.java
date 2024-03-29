package com.ddungja.petmily.apply.controller.response;

import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.domain.ApprovalType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplySupportResponse {
    private final Long applyId;
    private final ApprovalType approval;
    private final String job;
    private final String url;
    private final String environment;
    private final int people;
    private final String comment;
    private final String region;
    private final String nickname;
    private final Boolean isExperience;
    @Builder
    public ApplySupportResponse(Long applyId, ApprovalType approval, String job, String url, String environment, int people, String comment, String region, String nickname, Boolean isExperience) {
        this.applyId = applyId;
        this.approval = approval;
        this.job = job;
        this.url = url;
        this.environment = environment;
        this.people = people;
        this.comment = comment;
        this.region = region;
        this.nickname = nickname;
        this.isExperience = isExperience;
    }


    public static ApplySupportResponse from(Apply apply) {
        return ApplySupportResponse.builder()
                .applyId(apply.getId())
                .approval(apply.getApproval())
                .job(apply.getJob())
                .url(apply.getUrl())
                .environment(apply.getEnvironment())
                .nickname(apply.getUser().getNickname())
                .people(apply.getPeople())
                .comment(apply.getComment())
                .region(apply.getRegion())
                .isExperience(apply.isExperience())
                .build();
    }
}
