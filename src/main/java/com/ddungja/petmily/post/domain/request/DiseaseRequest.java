package com.ddungja.petmily.post.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class DiseaseRequest {
    private String name;
    @Builder
    public DiseaseRequest(String name) {
        this.name = name;
    }
}
