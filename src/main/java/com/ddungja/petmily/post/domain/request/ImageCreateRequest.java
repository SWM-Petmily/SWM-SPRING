package com.ddungja.petmily.post.domain.request;

import lombok.*;


@Getter
public class ImageCreateRequest {
    private final String url;
    private final String name;

    @Builder
    public ImageCreateRequest(String url, String name) {
        this.url = url;
        this.name = name;
    }
}
