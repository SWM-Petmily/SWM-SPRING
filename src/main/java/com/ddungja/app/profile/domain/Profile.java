package com.ddungja.app.profile.domain;


import lombok.Builder;
import lombok.Getter;

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

    @Builder
    public Profile(Long id, String job, String environment, String people, String comment, String color, String openTalk, String region, String isExperience) {
        this.id = id;
        this.job = job;
        this.environment = environment;
        this.people = people;
        this.comment = comment;
        this.color = color;
        this.openTalk = openTalk;
        this.region = region;
        this.isExperience = isExperience;
    }
}
