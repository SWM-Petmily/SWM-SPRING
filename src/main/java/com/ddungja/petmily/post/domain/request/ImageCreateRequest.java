package com.ddungja.petmily.post.domain.request;

import lombok.*;


@Getter
@NoArgsConstructor
public class ImageCreateRequest {
    private  String url;

    @Builder
    public ImageCreateRequest(String url) {
        this.url = url;
    }
}
