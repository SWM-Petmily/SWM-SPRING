package com.ddungja.petmily.apply.domain.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ApplyCreateRequest {
    private final Long profileImageId;
    private final String job;
    private final String environment;
    private final int people;
    private final String comment;
    private final String openTalk;
    private final String region;
    private final Boolean isExperience;
    private final String url;
    private final List<ApplyExperienceCreateRequest> experiences;

    @Builder
    public ApplyCreateRequest(Long profileImageId, String job, String environment, int people, String comment, String openTalk, String region, Boolean isExperience, String url, List<ApplyExperienceCreateRequest> experiences) {
        this.profileImageId = profileImageId;
        this.job = job;
        this.environment = environment;
        this.people = people;
        this.comment = comment;
        this.openTalk = openTalk;
        this.region = region;
        this.isExperience = isExperience;
        this.url = url;
        this.experiences = experiences;
    }
}
