package com.ddungja.petmily.like.controller.response;

import com.ddungja.petmily.like.domain.Like;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class LikeListResponse {
    private final int postCount;
    private final List<LikePostResponse> posts;

    @Builder
    public LikeListResponse(List<LikePostResponse> likePostResponses, int likeCount) {
        this.posts = likePostResponses;
        this.postCount = likeCount;
    }

    public static LikeListResponse from(List<Like> likes) {
        return LikeListResponse.builder()
                .likePostResponses(LikePostResponse.from(likes))
                .likeCount(likes.size())
                .build();
    }

}
