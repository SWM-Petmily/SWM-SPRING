package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.Image;
import com.ddungja.petmily.post.domain.type.ImageType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageResponse {

    private final String url;
    private final ImageType imageType;
    @Builder
    private ImageResponse(String url, ImageType imageType) {
        this.url = url;
        this.imageType = imageType;
    }
    public static ImageResponse from(Image image) {
        return ImageResponse.builder()
                .url(image.getUrl())
                .imageType(image.getImageType())
                .build();
    }
}
