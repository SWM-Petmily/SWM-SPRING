package com.ddungja.petmily.user.domain;



public enum ProviderType {
    KAKAO("카카오");
    private final String description;

    ProviderType(String description) {
        this.description = description;
    }
}
