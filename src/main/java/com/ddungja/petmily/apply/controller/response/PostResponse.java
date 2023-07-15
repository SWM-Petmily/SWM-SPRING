package com.ddungja.petmily.apply.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponse {

    private final Long postId;
    @Builder
    private PostResponse(Long id) {
        this.postId = id;
    }
}
