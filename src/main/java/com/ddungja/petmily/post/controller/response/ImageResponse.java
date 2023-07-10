package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.like.controller.response.LikePostResponse;
import com.ddungja.petmily.like.domain.Like;
import com.ddungja.petmily.post.domain.image.Image;
import com.ddungja.petmily.post.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ImageResponse {

    private String url;

    @Builder
    public ImageResponse(String url) {
        this.url = url;
    }
    public static List<ImageResponse> from(List<Image> images) {

        List<ImageResponse> imageResponses = new ArrayList<>();
        for(Image image : images){
            ImageResponse imageResponse = ImageResponse.builder()
                    .url(image.getUrl())
                    .build();
            imageResponses.add(imageResponse);
        }
        return imageResponses;
    }

}
