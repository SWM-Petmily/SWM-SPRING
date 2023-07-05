package com.ddungja.app.post.domain.post;

public enum PostStatusType {
    SAVE("분양중"), WAITING("임시저장"), COMPLETE("분양완료"), DELETE("삭제");

    private final String description;

    PostStatusType(String description) {
        this.description = description;
    }

}
