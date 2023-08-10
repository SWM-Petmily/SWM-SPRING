package com.ddungja.petmily.apply.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplyUpdateRequest {
    private final String comment;
    private final String openTalk;
    @Builder
    private ApplyUpdateRequest(String comment, String openTalk) {
        this.comment = comment;
        this.openTalk = openTalk;
    }
}
