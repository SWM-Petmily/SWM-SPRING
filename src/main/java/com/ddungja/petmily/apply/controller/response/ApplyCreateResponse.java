package com.ddungja.petmily.apply.controller.response;

import com.ddungja.petmily.apply.domain.Apply;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplyCreateResponse {
    private final Long applyId;

    @Builder
    private ApplyCreateResponse(Long applyId) {
        this.applyId = applyId;
    }

    public static ApplyCreateResponse from(Apply apply) {
        return ApplyCreateResponse.builder()
                .applyId(apply.getId())
                .build();
    }

}
