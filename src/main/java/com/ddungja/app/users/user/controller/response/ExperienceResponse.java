package com.ddungja.app.users.user.controller.response;


import com.ddungja.app.users.user.domain.Experience;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
public class ExperienceResponse {
    private final String species;
    private final int period;


    @Builder
    public ExperienceResponse(String species, int period) {
        this.species = species;
        this.period = period;
    }

    public static ExperienceResponse from(Experience experience) {
        return ExperienceResponse.builder()
                .species(experience.getSpecies())
                .period(experience.getPeriod())
                .build();
    }
}
