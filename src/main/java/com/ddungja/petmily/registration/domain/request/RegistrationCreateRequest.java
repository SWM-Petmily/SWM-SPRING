package com.ddungja.petmily.registration.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RegistrationCreateRequest {
    private final String ownerName;
    private final String dogRegistrationNumber;

    @Builder
    private RegistrationCreateRequest(String owner_nm, String dog_reg_no) {
        this.ownerName = owner_nm;
        this.dogRegistrationNumber = dog_reg_no;
    }
}
