package com.ddungja.petmily.post.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class DiseaseRequest {
    private String name;
    @Builder
    private DiseaseRequest(String name, String dasdf) {
        this.name = name;
    }
}
