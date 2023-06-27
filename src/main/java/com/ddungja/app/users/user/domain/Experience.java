package com.ddungja.app.users.user.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class Experience {
    private Long id;
    private String species;
    private int period;
    private Profile profile;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    @Builder
    private Experience(Long id, String species, int period, Profile profile, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.species = species;
        this.period = period;
        this.profile = profile;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static Experience from(Experience experience, Profile profile) {
        return Experience.builder()
                .species(experience.getSpecies())
                .period(experience.getPeriod())
                .profile(profile)
                .build();
    }

    public static Experience from(Experience experience) {
        return Experience.builder()
                .species(experience.getSpecies())
                .period(experience.getPeriod())
                .build();
    }
}
