package com.ddungja.petmily.apply.controller.response;

import com.ddungja.petmily.apply.domain.Apply;
import com.ddungja.petmily.apply.domain.ApprovalType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApprovalResponse {

    private final Long applyId;
    private final ApprovalType approval;

    @Builder
    public ApprovalResponse(Long applyId, ApprovalType approval) {
        this.applyId = applyId;
        this.approval = approval;
    }

    public static ApprovalResponse from(Apply apply) {
        return ApprovalResponse.builder()
                .applyId(apply.getId())
                .approval(apply.getApproval())
                .build();
    }
}
