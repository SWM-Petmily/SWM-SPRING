package com.ddungja.app.users.user.domain;



public enum Provider {

    KAKAO("카카오");


    private final String description;

    Provider(String description) {
        this.description = description;
    }
}
