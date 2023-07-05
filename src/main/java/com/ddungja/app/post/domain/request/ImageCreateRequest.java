package com.ddungja.app.post.domain.request;

import lombok.*;


@Getter
public class ImageCreateRequest {
    private String url;
    private String name;

    @Builder
    public ImageCreateRequest(String url, String name) {
        this.url = url;
        this.name = name;
    }
}
