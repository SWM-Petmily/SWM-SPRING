package com.ddungja.app.users.experience.domain;

import com.ddungja.app.users.profile.infrastructure.ProfileEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class Experience {
    private Long id;
    private String species;
    private int period;
    private ProfileEntity profile;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    @Builder
    private Experience(Long id, String species, int period, ProfileEntity profile, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.species = species;
        this.period = period;
        this.profile = profile;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
