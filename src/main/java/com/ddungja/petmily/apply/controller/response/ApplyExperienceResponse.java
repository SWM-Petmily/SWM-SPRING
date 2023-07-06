package com.ddungja.petmily.apply.controller.response;

import com.ddungja.petmily.apply.domain.ApplyExperience;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplyExperienceResponse {

    private final Long applyExperienceId;
    private final String species;
    private final int period;

    @Builder
    private ApplyExperienceResponse(Long applyExperienceId, String species, int period) {
        this.applyExperienceId = applyExperienceId;
        this.species = species;
        this.period = period;
    }

    public static ApplyExperienceResponse from(ApplyExperience applyExperience) {
        return ApplyExperienceResponse.builder()
                .applyExperienceId(applyExperience.getId())
                .species(applyExperience.getSpecies())
                .period(applyExperience.getPeriod())
                .build();
    }
}
