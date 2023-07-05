package com.ddungja.app.post.controller.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DiseaseDto {
    @Data
    public static class DiseaseRequest {
        private String name;
    }
}