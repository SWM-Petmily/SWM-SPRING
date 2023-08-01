package com.ddungja.petmily.user.controller.response;


import com.ddungja.petmily.user.domain.profile.Experience;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProfileExperienceResponse {
    private final String species;
    private final int period;
    @Builder
    private ProfileExperienceResponse(String species, int period) {
        this.species = species;
        this.period = period;
    }

    public static ProfileExperienceResponse from(Experience experience) {
        return ProfileExperienceResponse.builder()
                .species(experience.getSpecies())
                .period(experience.getPeriod())
                .build();
    }
}
