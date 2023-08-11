package com.ddungja.petmily.registration.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RegistrationCreateRequest {
    private final String ownerName;
    private final String dogRegistrationNumber;

    @Builder
    public RegistrationCreateRequest(String ownerName, String dogRegistrationNumber) {
        this.ownerName = ownerName;
        this.dogRegistrationNumber = dogRegistrationNumber;
    }
}
