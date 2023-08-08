package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.Post;
import lombok.Builder;
import lombok.Getter;


@Getter
public class PostCreateResponse {

    private final Long postId;
    @Builder
    public PostCreateResponse(Long postId) {
        this.postId = postId;
    }

    public static PostCreateResponse from(Post post) {
        return PostCreateResponse.builder()
                .postId(post.getId())
                .build();
    }

}
