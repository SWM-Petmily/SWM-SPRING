package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.Image;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageResponse {

    private final String url;
    @Builder
    private ImageResponse(String url) {
        this.url = url;
    }
    public static ImageResponse from(Image image) {
        return ImageResponse.builder()
                .url(image.getUrl())
                .build();
    }
}
