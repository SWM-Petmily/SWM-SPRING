package com.ddungja.app.users.profile.domain;


import com.ddungja.app.users.profileimage.infrastructure.ProfileImageEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Profile {
    private final Long id;
    private final String job;
    private final String environment;
    private final String people;
    private final String comment;
    private final String color;
    private final String openTalk;
    private final String region;
    private final String isExperience;
    private final ProfileImageEntity profileImage;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    @Builder
    private Profile(Long id, String job, String environment, String people, String comment, String color, String openTalk, String region, String isExperience, ProfileImageEntity profileImage, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.job = job;
        this.environment = environment;
        this.people = people;
        this.comment = comment;
        this.color = color;
        this.openTalk = openTalk;
        this.region = region;
        this.isExperience = isExperience;
        this.profileImage = profileImage;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
