package com.ddungja.petmily.post.domain.request;

import lombok.Builder;
import lombok.Getter;


@Getter
public class DiseaseRequest {
    private final String name;
    private final String dasdf;

    @Builder
    public DiseaseRequest(String name, String dasdf) {
        this.name = name;
        this.dasdf = dasdf;
    }
}
