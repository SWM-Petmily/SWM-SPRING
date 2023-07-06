package com.ddungja.petmily.apply.controller.response;


import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class ApplyPostResponse {
    private final PostResponse post;
    private final Page<ApplySupportResponse> applys;

    @Builder
    private ApplyPostResponse(PostResponse postResponse, Page<ApplySupportResponse> applyResponses) {
        this.post = postResponse;
        this.applys = applyResponses;
    }

    public static ApplyPostResponse from(Long postId, Page<ApplySupportResponse> byPostId) {
        return ApplyPostResponse.builder()
                .postResponse(PostResponse.builder()
                        .id(postId)
                        .build())
                .applyResponses(byPostId)
                .build();
    }
}
