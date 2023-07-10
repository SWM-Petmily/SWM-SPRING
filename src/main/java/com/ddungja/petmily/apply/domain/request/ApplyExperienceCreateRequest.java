package com.ddungja.petmily.apply.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplyExperienceCreateRequest {
    private final String species;
    private final int period;

    @Builder
    private ApplyExperienceCreateRequest(String species, int period) {
        this.species = species;
        this.period = period;
    }
}
