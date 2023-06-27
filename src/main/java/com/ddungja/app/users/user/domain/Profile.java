package com.ddungja.app.users.user.domain;


import com.ddungja.app.users.user.infrastructure.entity.ExperienceEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Profile {
    private final Long id;
    private final String job;
    private final String environment;
    private final int people;
    private final String comment;
    private final String color;
    private final String openTalk;
    private final String region;
    private final boolean isExperience;
    private final User user;
    private final ProfileImage profileImage;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;
    private List<Experience> experiences = new ArrayList<>();
    @Builder
    private Profile(Long id, String job, String environment, int people, String comment, String color, String openTalk, String region, boolean isExperience, User user,     List<Experience> experiences, ProfileImage profileImage, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.job = job;
        this.environment = environment;
        this.people = people;
        this.comment = comment;
        this.color = color;
        this.openTalk = openTalk;
        this.region = region;
        this.isExperience = isExperience;
        this.user = user;
        this.profileImage = profileImage;
        this.experiences = experiences;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static Profile from(ProfileCreateRequest profileCreateRequest, ProfileImage profileImage, User user) {
        return Profile.builder()
                .job(profileCreateRequest.getJob())
                .environment(profileCreateRequest.getEnvironment())
                .people(profileCreateRequest.getPeople())
                .comment(profileCreateRequest.getComment())
                .color(profileCreateRequest.getColor())
                .openTalk(profileCreateRequest.getOpenTalk())
                .region(profileCreateRequest.getRegion())
                .isExperience(profileCreateRequest.isExperience())
                .experiences(profileCreateRequest.getExperiences())
                .user(user)
                .profileImage(profileImage)
                .build();

    }
}
