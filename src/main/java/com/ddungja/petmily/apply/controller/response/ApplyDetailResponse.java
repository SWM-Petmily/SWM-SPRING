package com.ddungja.petmily.apply.controller.response;

import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.domain.ApprovalType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ApplyDetailResponse {
    private final Long applyId;
    private final String job;
    private final String environment;
    private final int people;
    private final String comment;
    private final String openTalk;
    private final String region;
    private final Boolean isExperience;
    private final String url;
    private final ApprovalType approval;
    private final List<ApplyExperienceResponse> applyExperiences;
    private final Boolean isMyApply;

    @Builder
    private ApplyDetailResponse(Long applyId, String job, String environment, int people, String comment, String openTalk, String region, Boolean isExperience, String url, ApprovalType approval, List<ApplyExperienceResponse> applyExperienceResponses, Boolean isMyApply) {
        this.applyId = applyId;
        this.job = job;
        this.environment = environment;
        this.people = people;
        this.comment = comment;
        this.openTalk = openTalk;
        this.region = region;
        this.isExperience = isExperience;
        this.url = url;
        this.approval = approval;
        this.applyExperiences = applyExperienceResponses;
        this.isMyApply = isMyApply;
    }

    public static ApplyDetailResponse from(Apply apply, boolean isMyApply) {
        return ApplyDetailResponse.builder()
                .applyId(apply.getId())
                .job(apply.getJob())
                .environment(apply.getEnvironment())
                .people(apply.getPeople())
                .comment(apply.getComment())
                .openTalk(apply.getOpenTalk())
                .region(apply.getRegion())
                .isExperience(apply.isExperience())
                .url(apply.getUrl())
                .approval(apply.getApproval())
                .applyExperienceResponses(apply.getApplyExperiences().stream().map(ApplyExperienceResponse::from).toList())
                .isMyApply(isMyApply)
                .build();
    }
}
