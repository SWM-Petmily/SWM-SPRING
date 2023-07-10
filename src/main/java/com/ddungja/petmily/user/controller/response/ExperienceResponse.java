package com.ddungja.petmily.user.controller.response;


import com.ddungja.petmily.user.domain.Experience;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ExperienceResponse {
    private final String species;
    private final int period;
    @Builder
    private ExperienceResponse(String species, int period) {
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
