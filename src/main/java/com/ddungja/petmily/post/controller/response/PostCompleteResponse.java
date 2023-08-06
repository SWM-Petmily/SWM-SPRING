package com.ddungja.petmily.post.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCompleteResponse {
    private final Long postId;

    @Builder
    private PostCompleteResponse(Long postId) {
        this.postId = postId;
    }

    public static PostCompleteResponse from(Long postId) {
        return PostCompleteResponse.builder()
                .postId(postId)
                .build();
    }

}
