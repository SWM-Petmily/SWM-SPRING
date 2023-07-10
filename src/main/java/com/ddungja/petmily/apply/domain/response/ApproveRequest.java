package com.ddungja.petmily.apply.domain.response;

import com.ddungja.petmily.apply.domain.ApprovalType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ApproveRequest {
    private ApprovalType approval;

    @Builder
    public ApproveRequest(ApprovalType approval) {
        this.approval = approval;
    }
}
