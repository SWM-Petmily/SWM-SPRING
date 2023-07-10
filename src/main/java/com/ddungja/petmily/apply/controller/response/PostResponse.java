package com.ddungja.petmily.apply.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponse {

    private Long postId;
    @Builder
    private PostResponse(Long id) {
        this.postId = id;
    }
}
