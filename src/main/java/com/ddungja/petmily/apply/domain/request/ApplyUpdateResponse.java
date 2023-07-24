package com.ddungja.petmily.apply.domain.request;


import com.ddungja.petmily.apply.domain.Apply;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplyUpdateResponse {

    private final Long applyId;

    @Builder
    private ApplyUpdateResponse(Long applyId) {
        this.applyId = applyId;
    }

    public static ApplyUpdateResponse from(Apply modify) {
        return ApplyUpdateResponse.builder()
                .applyId(modify.getId())
                .build();
    }
}
