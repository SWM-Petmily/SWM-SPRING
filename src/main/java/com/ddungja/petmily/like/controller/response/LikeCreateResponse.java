package com.ddungja.petmily.like.controller.response;


import com.ddungja.petmily.like.domain.Like;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeCreateResponse {
    private final Long likeId;

    @Builder
    private LikeCreateResponse(Long likeId) {
        this.likeId = likeId;
    }

    public static LikeCreateResponse from(Like Like) {
        return LikeCreateResponse.builder()
                .likeId(Like.getId())
                .build();
    }


}
