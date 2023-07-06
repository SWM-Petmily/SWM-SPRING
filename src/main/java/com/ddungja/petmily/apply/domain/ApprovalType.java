package com.ddungja.petmily.apply.domain;

public enum ApprovalType {
    APPROVED("승인"), REJECTED("거절"), WAITING("지원중");

    private final String description;

    ApprovalType(String description) {
        this.description = description;
    }
}
