package com.ddungja.petmily.user.domain;



public enum ProviderType {
    KAKAO("카카오"), APPLE("애플");
    private final String description;

    ProviderType(String description) {
        this.description = description;
    }
}
