package com.ddungja.petmily.user.domain.request;


import lombok.Builder;
import lombok.Getter;

@Getter
public class ExperienceCreateRequest {

    private final String species;
    private final int period;

    @Builder
    public ExperienceCreateRequest(String species, int period) {
        this.species = species;
        this.period = period;
    }
}
