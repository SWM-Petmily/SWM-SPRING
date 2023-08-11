package com.ddungja.petmily.like.controller.response;


import com.ddungja.petmily.like.domain.Like;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeCreateResponse {
    private final Long likeId;

    @Builder
    public LikeCreateResponse(Long likeId) {
        this.likeId = likeId;
    }

    public static LikeCreateResponse from(Like like) {
        return LikeCreateResponse.builder()
                .likeId(like.getId())
                .build();
    }


}
