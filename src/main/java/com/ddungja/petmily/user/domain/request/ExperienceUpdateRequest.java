package com.ddungja.petmily.user.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExperienceUpdateRequest {
    private final String species;
    private final int period;

    @Builder
    public ExperienceUpdateRequest( String species, int period) {
        this.species = species;
        this.period = period;
    }
}
