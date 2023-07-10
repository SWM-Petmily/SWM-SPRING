package com.ddungja.petmily.user.domain.request;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ProfileUpdateRequest {

    private Long profileImageId;
    private String job;
    private String environment;
    private int people;
    private String comment;
    private String color;
    private String openTalk;
    private String region;
    private boolean isExperience;
    private List<ExperienceUpdateRequest> experiences;

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
