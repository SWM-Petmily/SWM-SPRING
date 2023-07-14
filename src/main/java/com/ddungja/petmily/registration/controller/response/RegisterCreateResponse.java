package com.ddungja.petmily.registration.controller.response;

import com.ddungja.petmily.registration.domain.Registration;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterCreateResponse {
    
    private Long registerId;
    
    @Builder
    private RegisterCreateResponse(Long registerId) {
        this.registerId = registerId;
    }
    public static RegisterCreateResponse from(Registration registration) {
        return  RegisterCreateResponse.builder()
                .registerId(registration.getId())
                .build();
    }
}
