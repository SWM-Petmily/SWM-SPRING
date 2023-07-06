package com.ddungja.petmily.apply.controller.response;

import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.domain.ApprovalType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ApplySupportResponse {

    private final Long applyId;
    private final Long userId;
    private final Long sellerId;
    private final ApprovalType approval;
    private final String job;
    private final String environment;
    private final int people;
    private final String comment;
    private final String openTalk;
    private final String region;
    private final Boolean isExperience;
    private final String url;
    private final List<ApplyExperienceResponse> applyExperiences;

    @Builder
    private ApplySupportResponse(Long applyId, Long userId, Long sellerId, ApprovalType approval, String job, String environment, int people, String comment, String openTalk, String region, boolean isExperience, String url, List<ApplyExperienceResponse> applyExperiences) {
        this.applyId = applyId;
        this.userId = userId;
        this.sellerId = sellerId;
        this.approval = approval;
        this.job = job;
        this.environment = environment;
        this.people = people;
        this.comment = comment;
        this.openTalk = openTalk;
        this.region = region;
        this.isExperience = isExperience;
        this.url = url;
        this.applyExperiences = applyExperiences;
    }

    @Builder
    public static ApplySupportResponse from(Apply apply) {
        return ApplySupportResponse.builder()
                .applyId(apply.getId())
                .userId(apply.getUser().getId())
                .sellerId(apply.getSeller().getId())
                .approval(apply.getApproval())
                .job(apply.getJob())
                .environment(apply.getEnvironment())
                .people(apply.getPeople())
                .comment(apply.getComment())
                .openTalk(apply.getOpenTalk())
                .region(apply.getRegion())
                .isExperience(apply.isExperience())
                .url(apply.getUrl())
                .applyExperiences(apply.getApplyExperiences().stream().map(ApplyExperienceResponse::from).toList())
                .build();
    }


}
