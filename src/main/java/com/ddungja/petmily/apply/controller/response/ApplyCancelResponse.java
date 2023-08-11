package com.ddungja.petmily.apply.controller.response;

import com.ddungja.petmily.apply.domain.Apply;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplyCancelResponse {

    private final Long applyId;

    @Builder
    public ApplyCancelResponse(Long applyId) {
        this.applyId = applyId;
    }

    public static ApplyCancelResponse from(Apply apply) {
        return ApplyCancelResponse.builder()
                .applyId(apply.getId())
                .build();
    }
}
