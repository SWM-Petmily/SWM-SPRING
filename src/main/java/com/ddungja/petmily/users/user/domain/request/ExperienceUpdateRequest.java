package com.ddungja.petmily.users.user.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExperienceUpdateRequest {

    private final Long id;
    private final String species;
    private final int period;

    @Builder
    public ExperienceUpdateRequest(Long id, String species, int period) {
        this.id = id;
        this.species = species;
        this.period = period;
    }
}
