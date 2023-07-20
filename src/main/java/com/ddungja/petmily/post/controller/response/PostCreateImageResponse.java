package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.Image;
import com.ddungja.petmily.post.domain.type.ImageType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateImageResponse {

    private final String url;
    private final ImageType imageType;

    @Builder
    private PostCreateImageResponse(String url, ImageType imageType) {
        this.url = url;
        this.imageType = imageType;
    }

    public static PostCreateImageResponse from(Image image) {
        return PostCreateImageResponse.builder()
                .url(image.getUrl())
                .imageType(image.getImageType())
                .build();
    }
}
