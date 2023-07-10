package com.ddungja.petmily.user.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExperienceUpdateRequest {

    private Long id;
    private String species;
    private int period;

    @Builder
    public ExperienceUpdateRequest(Long id, String species, int period) {
        this.id = id;
        this.species = species;
        this.period = period;
    }
}
