package com.ddungja.app.apply.domain;

public enum ApprovalType {
    APPROVED("승인"), REJECTED("거절"), WAITING("대기");

    private final String description;

    ApprovalType(String description) {
        this.description = description;
    }
}
