package com.ddungja.petmily.users.user.domain.request;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProfileUpdateRequest {

    private final Long profileImageId;
    private final String job;
    private final String environment;
    private final int people;
    private final String comment;
    private final String color;
    private final String openTalk;
    private final String region;
    private final boolean isExperience;
    private final List<ExperienceUpdateRequest> experiences;

    @Builder
    public ProfileUpdateRequest(Long profileImageId, String job, String environment, int people, String comment, String color, String openTalk, String region, boolean isExperience, List<ExperienceUpdateRequest> experiences) {
        this.profileImageId = profileImageId;
        this.job = job;
        this.environment = environment;
        this.people = people;
        this.comment = comment;
        this.color = color;
        this.openTalk = openTalk;
        this.region = region;
        this.isExperience = isExperience;
        this.experiences = experiences;
    }
}
