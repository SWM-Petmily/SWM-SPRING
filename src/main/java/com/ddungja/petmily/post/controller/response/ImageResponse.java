package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageResponse {
    private  String url;
    @Builder
    public ImageResponse(String url) {
        this.url = url;
    }
    public static ImageResponse from(Image image) {
        return ImageResponse.builder()
                .url(image.getUrl())
                .build();
    }
}
