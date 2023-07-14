package com.ddungja.petmily.registration.domain.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public class RegistrationCreateRequest {
    private final String owner_nm;
    private final String dog_reg_no;
    private final String serviceKey = "iqucPIkN%2Br9Zm%2FI6ALZlkapFDvJIgC1gySMHPDOd0aTP%2BbDajaKTYeE680W97iVo0EEg4PZcEGozg37slj%2FlcA%3D%3D";

    @Builder
    public RegistrationCreateRequest(String owner_nm, String dog_reg_no) {
        this.owner_nm = owner_nm;
        this.dog_reg_no = dog_reg_no;
    }
}
