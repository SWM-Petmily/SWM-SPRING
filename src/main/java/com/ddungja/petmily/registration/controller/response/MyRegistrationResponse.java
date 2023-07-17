package com.ddungja.petmily.registration.controller.response;

import com.ddungja.petmily.post.domain.type.GenderType;
import com.ddungja.petmily.post.domain.type.NeuteredType;
import com.ddungja.petmily.registration.domain.Registration;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MyRegistrationResponse {
    private final Long id;
    private final String petName;
    private final String petType;
    private final GenderType petGender;
    private final NeuteredType petNeutered;

    @Builder
    private MyRegistrationResponse(Long id, String petName, String petType, GenderType petGender, NeuteredType petNeutered) {
        this.id = id;
        this.petName = petName;
        this.petType = petType;
        this.petGender = petGender;
        this.petNeutered = petNeutered;
    }

    public static MyRegistrationResponse from(Registration registration){
        return MyRegistrationResponse.builder()
                .id(registration.getId())
                .petName(registration.getPetName())
                .petType(registration.getPetSubCategory().getName())
                .petGender(registration.getPetGender())
                .petNeutered(registration.getPetNeutered())
                .build();
    }
}
