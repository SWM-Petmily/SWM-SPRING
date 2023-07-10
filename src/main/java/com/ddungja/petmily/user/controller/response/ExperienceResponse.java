package com.ddungja.petmily.user.controller.response;


import com.ddungja.petmily.user.domain.Experience;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ExperienceResponse {

    private final Long id;
    private final String species;
    private final int period;


    @Builder
    public ExperienceResponse(Long id, String species, int period) {
        this.id = id;
        this.species = species;
        this.period = period;
    }

    public static ExperienceResponse from(Experience experience) {
        return ExperienceResponse.builder()
                .id(experience.getId())
                .species(experience.getSpecies())
                .period(experience.getPeriod())
                .build();
    }
}
