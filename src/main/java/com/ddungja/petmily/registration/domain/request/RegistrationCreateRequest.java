package com.ddungja.petmily.registration.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RegistrationCreateRequest {
    private final String owner_nm;
    private final String dog_reg_no;

    @Builder
    private RegistrationCreateRequest(String owner_nm, String dog_reg_no) {
        this.owner_nm = owner_nm;
        this.dog_reg_no = dog_reg_no;
    }
}
